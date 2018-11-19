package ar.edu.uba.fi.tdp2.guaraniapp.model;

import com.google.gson.annotations.SerializedName;

public class Horario {
    private String dia;
    @SerializedName("horario_desde")
    private String horaInicio;
    @SerializedName("horario_hasta")
    private String horaFin;
    private String aula = "-";
    private String sede = "-";

    private String tipo = "Teórico-Práctica Obligatoria";

    public Horario(String dia, String horaInicio, String horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Horario(String dia, String horaInicio, String horaFin, String aula, String sede) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.aula = aula;
        this.sede = sede;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSede() {
        if (sede == null)
            return "-";
        switch (sede) {
            case "PC":
                return "Paseo Colón";
            case "LH":
                return "Las Heras";
            case "CU":
                return "Ciudad Universitaria";
            default:
                return sede;
        }
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
}
