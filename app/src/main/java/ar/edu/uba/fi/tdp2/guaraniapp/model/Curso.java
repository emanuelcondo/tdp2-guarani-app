package ar.edu.uba.fi.tdp2.guaraniapp.model;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.model.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Persona;

public class Curso {
    private String _id;
    private int comision;
    private Persona docenteACargo;
    private Persona jtp;
    private List<Horario> cursada;
    private List<Persona> ayudantes = new ArrayList<>();
    private int vacantes;
    private int cupos;

    public Curso(int numeroCurso, ArrayList<Horario> horarios, int vacantes) {
        this.comision = numeroCurso;
        this.cursada = horarios;
        this.vacantes = vacantes;
    }

    //Constructor de Curso Condicional
    public Curso() {
        this.comision = 0;
        this.cursada = new ArrayList<>();
        this.cupos = 0;
        this.vacantes = 0;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
        if (vacantes > 0)
            --vacantes;
    }

    public void agregarVacante() {
        if (vacantes < cupos)
            ++vacantes;
    }

    public List<Persona> getAyudantes() {
        return ayudantes;
    }

    public void setAyudantes(List<Persona> ayudantes) {
        this.ayudantes = ayudantes;
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

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

}
