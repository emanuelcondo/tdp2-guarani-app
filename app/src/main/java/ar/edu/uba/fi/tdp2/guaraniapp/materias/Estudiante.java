package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import java.util.ArrayList;
import java.util.HashMap;

public class Estudiante {
    private int padron;
    private String nombre;
    private String apellido;
    private HashMap<Curso, Boolean> inscripciones = new HashMap<>();

    public Estudiante(int padron, String nombre, String apellido) {
        this.padron = padron;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public void inscribir(Curso curso, boolean condicional) {
        inscripciones.put(curso, condicional);
    }

    public ArrayList<Curso> getInscripciones() {
        return new ArrayList<>(inscripciones.keySet());
    }

    public void desinscribir(Curso curso) {
        inscripciones.remove(curso);
    }

    public boolean esCondicional(Curso curso) {
        return inscripciones.get(curso);
    }
}
