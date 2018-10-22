package ar.edu.uba.fi.tdp2.guaraniapp.examenes;

import java.io.Serializable;

public class Aula implements Serializable {
    private String sede;
    private String aula;
    private int capacidad;

    public Aula() {}

    public String getSede() {
        switch (sede) {
            case "CU": return "Ciudad Universitaria";
            case "PC": return "Paseo Colón";
            case "LH": return "Las Heras";
            default: return "-";
        }
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
