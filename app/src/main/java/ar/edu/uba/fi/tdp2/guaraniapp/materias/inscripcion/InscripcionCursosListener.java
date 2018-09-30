package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

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
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;

public class InscripcionCursosListener implements ResponseListener {
    private Context context;

    public InscripcionCursosListener(Context context) {
        this.context = context;
    }

    @Override
    public void onRequestCompleted(Object response) {
        try {

            JSONObject jo = ((JSONObject)response).getJSONObject("data");

            String json = jo.getJSONArray("cursos").toString();
            Log.d("InscripcionCursosListener", json);

            Type listType = new TypeToken<ArrayList<Curso>>(){}.getType();
            List<Curso> ms = new Gson().fromJson(json, listType);
            List<Curso> cursos = new ArrayList<>(ms);

            ((MainActivity)context).getMateriaSeleccionada().setCursos(cursos);
            FragmentLoader.load((Activity) context, new InscripcionCursosFragment(), "InscripcionCursos");

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
