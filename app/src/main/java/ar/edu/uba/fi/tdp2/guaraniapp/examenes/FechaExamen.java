package ar.edu.uba.fi.tdp2.guaraniapp.examenes;

import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Persona;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Sede;

public class FechaExamen {
    private String _id;

    private Sede sede;
    private Persona docenteACargo;
    private Curso curso;
    private String aula;

    private String fecha;
    private String hora;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDocente() {
        return docenteACargo.toString();
    }

    public Persona getDocenteACargo() {
        return docenteACargo;
    }

    public void setDocenteACargo(Persona docenteACargo) {
        this.docenteACargo = docenteACargo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }


    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }
}
