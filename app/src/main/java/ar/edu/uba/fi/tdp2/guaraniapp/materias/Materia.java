package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import java.util.ArrayList;

public class Materia {
    private String codigo;
    private String nombre;
    private ArrayList<Curso> cursos = new ArrayList<>();

    public Materia(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void agregarCurso(Curso curso) {
        this.cursos.add(curso);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
