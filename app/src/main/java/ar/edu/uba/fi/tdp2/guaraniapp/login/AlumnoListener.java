package ar.edu.uba.fi.tdp2.guaraniapp.login;

import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta.OfertaCarrerasFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Alumno;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionCarrerasFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Periodo;

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

            String periodoJson = ((JSONObject)response).getJSONObject("data").getJSONObject("periodo").toString();
            Periodo periodo = new Gson().fromJson(periodoJson, Periodo.class);

            Log.d("AlumnoListener", alumno.toString());

            activity.setAlumno(alumno);
            activity.setPeriodo(periodo);

            // Para que se vea el nombre del alumno en el menu
            TextView view = activity.findViewById(R.id.alumno_conectado);
            view.setText(activity.getString(R.string.alumno_header, alumno.getApellido(), alumno.getNombre()));
            view = activity.findViewById(R.id.padron_alumno_conectado);
            view.setText(activity.getString(R.string.legajo_header, alumno.getLegajo()));
            //Agregar una foto al menu

            FragmentLoader.load(getActivity(), new OfertaCarrerasFragment(), "OfertaCarreras");

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
