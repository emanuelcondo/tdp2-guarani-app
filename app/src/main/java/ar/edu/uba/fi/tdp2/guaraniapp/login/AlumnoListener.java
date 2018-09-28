package ar.edu.uba.fi.tdp2.guaraniapp.login;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Alumno;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionCarrerasFragment;

public class AlumnoListener implements ResponseListener {
    private MainActivity activity;

    public AlumnoListener(MainActivity mainActivity) {
        activity = mainActivity;
    }

    @Override
    public void onRequestCompleted(Object response) {
        try {
            String alumnoJson = ((JSONObject)response).getJSONObject("data").getJSONObject("alumno").toString();

            Alumno alumno = new Gson().fromJson(alumnoJson, Alumno.class);

            Log.d("AlumnoListener", alumno.toString());

            getActivity().setUsuario(alumno);

            FragmentLoader.load(getActivity(), new InscripcionCarrerasFragment(), "InscripcionCarreras");

        } catch (JSONException e) {
            RequestHelper.showError(getActivity(), e.getMessage());
        }
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(getActivity(), errorMessage);
    }

    public MainActivity getActivity() {
        return activity;
    }
}