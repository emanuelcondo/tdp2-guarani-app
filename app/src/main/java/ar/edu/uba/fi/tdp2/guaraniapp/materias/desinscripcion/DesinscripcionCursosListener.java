package ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion;

import android.app.Activity;
import android.content.Context;

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
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Inscripcion;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionCursosFragment;

public class DesinscripcionCursosListener implements ResponseListener {
    private Context context;
    private DesinscripcionCursosFragment fragment;

    public DesinscripcionCursosListener(Context context, DesinscripcionCursosFragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public void onRequestCompleted(Object response) {
        try {

            JSONObject jo = ((JSONObject)response).getJSONObject("data");
            Type listType = new TypeToken<ArrayList<Inscripcion>>(){}.getType();
            List<Inscripcion> inscripciones = new Gson().fromJson(jo.getJSONArray("inscripciones").toString(), listType);

            fragment.onSuccess(inscripciones);

        } catch (JSONException e) {
            e.printStackTrace();
            RequestHelper.showError(context, e.getMessage());
        }
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(context, errorMessage);
    }

}
