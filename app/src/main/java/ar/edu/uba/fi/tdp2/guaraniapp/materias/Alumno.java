package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    //uso legajo en lugar de padron porque asi viene en el server
    private int legajo;
    private String nombre;
    private String apellido;
    private List<Carrera> carreras = new ArrayList<>();
    private List<Inscripcion> inscripciones;

    public Alumno() {
        carreras = new ArrayList<>();
        inscripciones = new ArrayList<>();
    }

    public Alumno(int padron, String nombre, String apellido) {
        this.legajo = padron;
        this.nombre = nombre;
        this.apellido = apellido;
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

        return "(" + legajo + ") " + nombre + " " + apellido ;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public void eliminarInscripcion(Inscripcion inscripcion) {
        inscripciones.remove(inscripcion);
    }
}
