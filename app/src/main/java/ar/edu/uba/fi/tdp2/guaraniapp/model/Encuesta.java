package ar.edu.uba.fi.tdp2.guaraniapp.model;

public class Encuesta {
    private int nivelGeneral;
    private int nivelTeoricas;
    private int nivelPracticas;
    private int nivelTemas;
    private int nivelActualizacion;
    private String comentario;

    public Encuesta (int nivelGeneral,
                     int nivelTeoricas,
                     int nivelPracticas,
                     int nivelTemas,
                     int nivelActualizacion,
                     String comentario) {
        this.nivelGeneral = nivelGeneral;
        this.nivelTeoricas = nivelTeoricas;
        this.nivelPracticas = nivelPracticas;
        this.nivelTemas = nivelTemas;
        this.nivelActualizacion = nivelActualizacion;
        this.comentario = comentario;
    }

    public int getNivelGeneral() {
        return nivelGeneral;
    }

    public void setNivelGeneral(int nivelGeneral) {
        this.nivelGeneral = nivelGeneral;
    }

    public int getNivelTeoricas() {
        return nivelTeoricas;
    }

    public void setNivelTeoricas(int nivelTeoricas) {
        this.nivelTeoricas = nivelTeoricas;
    }

    public int getNivelPracticas() {
        return nivelPracticas;
    }

    public void setNivelPracticas(int nivelPracticas) {
        this.nivelPracticas = nivelPracticas;
    }

    public int getNivelTemas() {
        return nivelTemas;
    }

    public void setNivelTemas(int nivelTemas) {
        this.nivelTemas = nivelTemas;
    }

    public int getNivelActualizacion() {
        return nivelActualizacion;
    }

    public void setNivelActualizacion(int nivelActualizacion) {
        this.nivelActualizacion = nivelActualizacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
