package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int comision;
    private Sede sede;
    private Persona docenteACargo;
    private Persona jtp;
    private List<Horario> cursada;
    private List<Persona> ayudantes = new ArrayList<>();
    private int vacantes;

    public Curso(int numeroCurso, ArrayList<Horario> horarios, int vacantes) {
        this.comision = numeroCurso;
        this.cursada = horarios;
        this.vacantes = vacantes;
    }

    public String getDocente() {
        return docenteACargo.toString();
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    public List<Horario> getCursada() {
        return cursada;
    }

    public void setCursada(List<Horario> cursada) {
        this.cursada = cursada;
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

    public List<Persona> getAyudantes() {
        return ayudantes;
    }

    public void setAyudantes(List<Persona> ayudantes) {
        this.ayudantes = ayudantes;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Persona getDocenteACargo() {
        return docenteACargo;
    }

    public void setDocenteACargo(Persona docenteACargo) {
        this.docenteACargo = docenteACargo;
    }

    public Persona getJtp() {
        return jtp;
    }

    public void setJtp(Persona jtp) {
        this.jtp = jtp;
    }
}
