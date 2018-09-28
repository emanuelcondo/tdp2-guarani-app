package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Alumno {
    //uso legajo en lugar de padron porque asi viene en el server
    private int legajo;
    private String nombre;
    private String apellido;
    private HashMap<Curso, Boolean> inscripciones = new HashMap<>();
    private List<Carrera> carreras = new ArrayList<>();

    public Alumno() {
        carreras = new ArrayList<>();
    }
    public Alumno(int padron, String nombre, String apellido) {
        this.legajo = padron;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public void inscribir(Curso curso, boolean condicional) {
        inscripciones.put(curso, condicional);
    }

    //TODO: esto hay que buscarlo del server
    public ArrayList<Curso> getInscripciones() {
        return new ArrayList<>(inscripciones.keySet());
    }

    //TODO: esto hay que pegarle al server, no se guarda aca
    public void desinscribir(Curso curso) {
        inscripciones.remove(curso);
    }

    //TODO: esto va a venir en un flag dentro de las inscripciones
    public boolean esCondicional(Curso curso) {
        return inscripciones.get(curso);
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    @Override
    public String toString() {
        String strCarreras = "/";
        for (Carrera carrera: carreras) {
            strCarreras += carrera.getNombre() + "/";
        }

        return "(" + legajo + ") " + nombre + apellido + strCarreras;
    }
}
