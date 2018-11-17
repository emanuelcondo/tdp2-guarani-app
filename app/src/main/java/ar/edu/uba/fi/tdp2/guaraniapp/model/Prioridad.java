package ar.edu.uba.fi.tdp2.guaraniapp.model;

import java.io.Serializable;

public class Prioridad implements Serializable {
    private int numero;
    private String fechaDeInscripcion;


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFechaDeInscripcion() {
        return fechaDeInscripcion;
    }

    public void setFechaDeInscripcion(String fechaDeInscripcion) {
        this.fechaDeInscripcion = fechaDeInscripcion;
    }
}
