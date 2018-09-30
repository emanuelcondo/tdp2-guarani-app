package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

import android.app.Activity;
import android.content.Context;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Persona;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Sede;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.reflect.TypeToken;

public class InscripcionMateriasListener implements ResponseListener {
    private Context context;
    ResponseWatcher watcher;

    public InscripcionMateriasListener(Context context, ResponseWatcher watcher) {
        this.context = context;
        this.watcher = watcher;
    }

    @Override
    public void onRequestCompleted(Object response) {
        try {

            JSONObject jo = ((JSONObject)response).getJSONObject("data");
            Type listType = new TypeToken<ArrayList<Materia>>(){}.getType();
            List<Materia> ms = new Gson().fromJson(jo.getJSONArray("materias").toString(), listType);
            List<Materia> materias = new ArrayList<>(ms);

            watcher.onSuccess();

            ((MainActivity)context).setMaterias(materias);
            FragmentLoader.load((Activity) context, new InscripcionMateriasFragment(), "InscripcionMaterias");

        } catch (JSONException e) {
            e.printStackTrace();
            RequestHelper.showError(context, e.getMessage());
            watcher.onError();
        }
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(context, errorMessage);
        watcher.onError();
    }

}
