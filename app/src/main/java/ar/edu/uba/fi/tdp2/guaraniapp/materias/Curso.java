package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import java.util.ArrayList;

public class Curso {
    private int numeroCurso;
    private String docente;
    private ArrayList<Horario> horarios;
    private ArrayList<String> ayudantes = new ArrayList<>();
    private int vacantes;

    public Curso(int numeroCurso, String docente, ArrayList<Horario> horarios, int vacantes) {
        this.numeroCurso = numeroCurso;
        this.docente = docente;
        this.horarios = horarios;
        this.vacantes = vacantes;
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

    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }

    public int getVacantes() {
        return vacantes;
    }

    public void decrementarVacantes() {
        --vacantes;
    }

    public void agregarVacante() {
        ++vacantes;
    }

    public ArrayList<String> getAyudantes() {
        return ayudantes;
    }

    public void setAyudantes(ArrayList<String> ayudantes) {
        this.ayudantes = ayudantes;
    }
}
