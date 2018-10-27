package ar.edu.uba.fi.tdp2.guaraniapp.model;

import java.util.Date;

import ar.edu.uba.fi.tdp2.guaraniapp.model.Materia;

public class ResultadoExamen {
    private Materia materia;
    private Date fecha;
    private int nota;

    public ResultadoExamen(Materia materia, Date fecha, int nota) {
        this.materia = materia;
        this.fecha = fecha;
        this.nota = nota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
