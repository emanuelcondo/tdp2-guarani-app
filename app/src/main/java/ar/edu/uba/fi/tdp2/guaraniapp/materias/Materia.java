package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import java.util.ArrayList;
import java.util.List;

public class Materia {
    private String _id;
    private String codigo;
    private String nombre;

    private int creditos;
    private String departamento;
    private List<Curso> cursos;

    public Materia() {
        setCursos(new ArrayList<Curso>());
    }

    public Materia(String codigo, String nombre, String departamento) {
        setCodigo(codigo);
        setNombre(nombre);
        setDepartamento(departamento);
        setCursos(new ArrayList<Curso>());
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Curso> getCursos() {
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public boolean equals( Object obj) {
        Materia materia = (Materia)obj;
        return materia.getCodigo().equals(this.codigo);
    }
}
