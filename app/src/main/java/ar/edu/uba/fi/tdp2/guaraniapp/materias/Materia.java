package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import java.util.List;

public class Materia {
    private String codigo;
    private List<Curso> cursos;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
