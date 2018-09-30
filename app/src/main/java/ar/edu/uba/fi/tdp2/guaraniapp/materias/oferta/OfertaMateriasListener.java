package ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Persona;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionMateriasFragment;

public class OfertaMateriasListener implements ResponseListener {
    private Context context;
    private ResponseWatcher watcher;

    public OfertaMateriasListener(Context context, ResponseWatcher watcher) {
        this.context = context;
        this.watcher = watcher;
    }

    @Override
    public void onRequestCompleted(Object response) {
        try {

            JSONObject jo = ((JSONObject)response).getJSONObject("data");

            Log.d("OfertaMateriasListener", jo.toString());

            Type listType = new TypeToken<ArrayList<Materia>>(){}.getType();
            List<Materia> ms = new Gson().fromJson(jo.getJSONArray("materias").toString(), listType);
            List<Materia> materias = new ArrayList<>(ms);

            watcher.onSuccess();

            ((MainActivity)context).setMaterias(materias);
            FragmentLoader.load((Activity) context, new OfertaMateriasFragment(), "OfertaMaterias");

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
