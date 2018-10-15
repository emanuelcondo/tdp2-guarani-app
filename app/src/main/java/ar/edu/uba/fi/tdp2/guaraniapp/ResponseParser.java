package ar.edu.uba.fi.tdp2.guaraniapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.examenes.FechaExamen;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.InscripcionExamen;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Inscripcion;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;

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

    public static List<FechaExamen> getFechasExamen(Object response) {
        Type listType = new TypeToken<ArrayList<FechaExamen>>(){}.getType();
        return gson.fromJson(getJsonArray(response,"examenes"), listType);
    }

    public static List<InscripcionExamen> getInscripcionesExamenes(Object response) {
        Type listType = new TypeToken<ArrayList<InscripcionExamen>>(){}.getType();
        return gson.fromJson(getJsonArray(response,"inscripciones"), listType);
    }
}
