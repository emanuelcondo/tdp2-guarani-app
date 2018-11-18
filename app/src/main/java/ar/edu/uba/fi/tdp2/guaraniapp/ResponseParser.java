package ar.edu.uba.fi.tdp2.guaraniapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.model.EncuestaCurso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Examen;
import ar.edu.uba.fi.tdp2.guaraniapp.model.InscripcionExamen;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Inscripcion;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Materia;

public class ResponseParser {

    private static Gson gson = new Gson();

    private static String getJsonArray(Object response, String field) {
        try {
            JSONObject jo = ((JSONObject)response).getJSONObject("data");

            return jo.getJSONArray(field).toString();

        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());

        }
    }

    public static List<Inscripcion> getInscripciones(Object response) {
        Type listType = new TypeToken<ArrayList<Inscripcion>>(){}.getType();
        return gson.fromJson(getJsonArray(response,"inscripciones"), listType);
    }

    public static List<Materia> getMaterias(Object response) {
        Type listType = new TypeToken<ArrayList<Materia>>(){}.getType();
        return gson.fromJson(getJsonArray(response,"materias"), listType);
    }

    public static List<Curso> getCursos(Object response) {
        Type listType = new TypeToken<ArrayList<Curso>>(){}.getType();
        return gson.fromJson(getJsonArray(response,"cursos"), listType);
    }

    public static List<Examen> getFechasExamen(Object response) {
        Type listType = new TypeToken<ArrayList<Examen>>(){}.getType();
        return gson.fromJson(getJsonArray(response,"examenes"), listType);
    }

    public static List<InscripcionExamen> getInscripcionesExamenes(Object response) {
        Type listType = new TypeToken<ArrayList<InscripcionExamen>>(){}.getType();
        return gson.fromJson(getJsonArray(response,"inscripciones"), listType);
    }

    public static List<EncuestaCurso> getEncuestaCursos(Object response) {
        Type listType = new TypeToken<ArrayList<EncuestaCurso>>(){}.getType();
        return gson.fromJson(getJsonArray(response,"cursos"), listType);
    }
}
