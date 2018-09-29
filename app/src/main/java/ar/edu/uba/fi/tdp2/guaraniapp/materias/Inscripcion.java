package ar.edu.uba.fi.tdp2.guaraniapp.materias;

public class Inscripcion {
    private String timestamp;
    private String _id;
    private Curso curso;
    private Materia materia;
    private String condicion;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean esCondicional() {
        return this.condicion.equals("Condicional");
    }
}
