package ar.edu.uba.fi.tdp2.guaraniapp.model;

import ar.edu.uba.fi.tdp2.guaraniapp.model.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Materia;

public class EncuestaCurso {
    private Curso curso;
    private int comision;
    private int anio;
    private int cuatrimestre;
    private Materia materia;

    public EncuestaCurso (Curso curso,
                          int comision,
                          int anio,
                          int cuatrimestre,
                          Materia materia) {
        this.curso = curso;
        this.comision = comision;
        this.anio = anio;
        this.cuatrimestre = cuatrimestre;
        this.materia = materia;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
