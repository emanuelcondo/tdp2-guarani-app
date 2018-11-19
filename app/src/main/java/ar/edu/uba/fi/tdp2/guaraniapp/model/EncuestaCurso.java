package ar.edu.uba.fi.tdp2.guaraniapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ar.edu.uba.fi.tdp2.guaraniapp.model.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Materia;

public class EncuestaCurso implements Serializable {
    @SerializedName("_id")
    private String id;
    private int comision;
    private int anio;
    private int cuatrimestre;
    private Materia materia;

    public EncuestaCurso (String id,
                          int comision,
                          int anio,
                          int cuatrimestre,
                          Materia materia) {
        this.comision = comision;
        this.anio = anio;
        this.cuatrimestre = cuatrimestre;
        this.materia = materia;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
