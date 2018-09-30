package ar.edu.uba.fi.tdp2.guaraniapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
}
