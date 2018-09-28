package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

import android.app.Activity;
import android.content.Context;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Persona;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.reflect.TypeToken;

public class InscripcionMateriasListener implements ResponseListener {
    private Context context;

    public InscripcionMateriasListener(Context context) {
        this.context = context;
    }

    @Override
    public void onRequestCompleted(Object response) {
        try {

            JSONObject jo = ((JSONObject)response).getJSONObject("data");
            Type listType = new TypeToken<ArrayList<Materia>>(){}.getType();
            List<Materia> ms = new Gson().fromJson(jo.getJSONArray("materias").toString(), listType);
            List<Materia> materias = new ArrayList<>(ms);

            ((MainActivity)context).setMaterias(materias);
            FragmentLoader.load((Activity) context, new InscripcionMateriasFragment(), "InscripcionMaterias");

        } catch (JSONException e) {
            e.printStackTrace();
            RequestHelper.showError(context, e.getMessage());
        }
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(context, errorMessage);
    }

    public static void moquear(Context context) {
        String stringResponse = "{\"status\": \"success\", \"data\": { \"materias\": [ { \"_id\": \"a2bc2187abc8fe8a8dcb7101\", \"codigo\": \"7547\", \"subcodigo\": \"75\", \"nombre\": \"Taller de Desarrollo de Proyectos II\" }, { \"_id\": \"a2bc2187abc8fe8a8dcb7102\", \"codigo\": \"7512\", \"subcodigo\": \"75\", \"nombre\": \"Análisis numérico\" }, { \"_id\": \"a2bc2187abc8fe8a8dcb71\", \"codigo\": \"6203\", \"subcodigo\": \"62\", \"nombre\": \"Física II\" }, { \"_id\": \"a2bc2187abc8fe8a8dcb7121\", \"codigo\": \"7544\", \"subcodigo\": \"75\", \"nombre\": \"Administración y Control de Proyectos\" }, { \"_id\": \"a2bc2187abc8fe8a8dcb7121\", \"codigo\": \"7545\", \"subcodigo\": \"75\", \"nombre\": \"Taller de Desarrollo de Proyectos I\" }, { \"_id\": \"a2bc2187abc8fe8a8dcb7121\", \"codigo\": \"7507\", \"subcodigo\": \"75\", \"nombre\": \"Algoritmos III\" }, { \"_id\": \"a2bc2187abc8fe8a8dcb7121\", \"codigo\": \"7508\", \"subcodigo\": \"75\", \"nombre\": \"Sistemas Operativos\" } ] } } ";
        try {
            JSONObject jo = new JSONObject(stringResponse).getJSONObject("data");
            Type listType = new TypeToken<ArrayList<Materia>>(){}.getType();
            List<Materia> ms = new Gson().fromJson(jo.getJSONArray("materias").toString(), listType);
            List<Materia> materias = new ArrayList<>(ms);
            for (Materia m: materias) {
                ArrayList<Horario> horarios1 = new ArrayList<Horario>() {{
                    add(new Horario("Lunes","19","22", "Paseo Colón", "LB"));
                    add(new Horario("Martes", "15", "18", "Las Heras", "303"));
                }};
                Curso curso1 = new Curso(12, horarios1, 0);
                curso1.setDocenteACargo(new Persona("Gustavo", "Campagnuolo"));

                curso1.setAyudantes(new ArrayList<Persona>() {{
                    add(new Persona("Claudio", "Ubeda"));
                    add(new Persona("Gabriel", "Loeschbor"));
                }});
                m.agregarCurso(curso1);
            }
            ((MainActivity)context).setMaterias(materias);
        } catch (JSONException e) {
            e.printStackTrace();
            RequestHelper.showError(context, e.getMessage());
        }

    }
}
