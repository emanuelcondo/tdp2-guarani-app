package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import java.util.ArrayList;

public class Curso {
    private int numeroCurso;
    private String docente;
    private ArrayList<Horario> horarios;
    private String observaciones = "";

    public Curso(int numeroCurso, String docente, ArrayList<Horario> horarios) {
        this.numeroCurso = numeroCurso;
        this.docente = docente;
        this.horarios = horarios;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public int getNumeroCurso() {
        return numeroCurso;
    }

    public void setNumeroCurso(int numeroCurso) {
        this.numeroCurso = numeroCurso;
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
