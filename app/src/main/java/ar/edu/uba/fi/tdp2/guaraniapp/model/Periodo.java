package ar.edu.uba.fi.tdp2.guaraniapp.model;

public class Periodo {
    private int id;
    private int cuatrimestre;
    private int anio;
    private Plazo inscripcionCurso;
    private Plazo desinscripcionCurso;
    private Plazo cursada;
    private Plazo consultaPrioridad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Plazo getInscripcionCurso() {
        return inscripcionCurso;
    }

    public void setInscripcionCurso(Plazo inscripcionCurso) {
        this.inscripcionCurso = inscripcionCurso;
    }

    public Plazo getDesinscripcionCurso() {
        return desinscripcionCurso;
    }

    public void setDesinscripcionCurso(Plazo desinscripcionCurso) {
        this.desinscripcionCurso = desinscripcionCurso;
    }

    public Plazo getCursada() {
        return cursada;
    }

    public void setCursada(Plazo cursada) {
        this.cursada = cursada;
    }

    public Plazo getConsultaPrioridad() {
        return consultaPrioridad;
    }

    public void setConsultaPrioridad(Plazo consultaPrioridad) {
        this.consultaPrioridad = consultaPrioridad;
    }
}
