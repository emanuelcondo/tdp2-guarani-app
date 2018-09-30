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

    public static List<Inscripcion> getInscripciones(Object response) {
        try {
            JSONObject jo = ((JSONObject)response).getJSONObject("data");
            Type listType = new TypeToken<ArrayList<Inscripcion>>(){}.getType();
            return gson.fromJson(jo.getJSONArray("inscripciones").toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());

        }
    }

    public static List<Materia> getMaterias(Object response) {
        try {
            JSONObject jo = ((JSONObject)response).getJSONObject("data");
            Type listType = new TypeToken<ArrayList<Materia>>(){}.getType();
            return gson.fromJson(jo.getJSONArray("materias").toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());

        }
    }
}
