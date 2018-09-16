package ar.edu.uba.fi.tdp2.guaraniapp.materias;

public class Curso {
    private String docente;
    private String horario;

    public Curso(String docente, String horario) {
        this.docente = docente;
        this.horario = horario;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
