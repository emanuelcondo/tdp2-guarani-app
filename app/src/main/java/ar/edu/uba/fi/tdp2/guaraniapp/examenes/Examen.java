package ar.edu.uba.fi.tdp2.guaraniapp.examenes;

import java.util.ArrayList;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Persona;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Sede;

public class Examen {
    private String _id;

    private Sede sede;
    private Persona docenteACargo;
    private Curso curso;
    private String aula;
    private String oportunidad;
    private String fecha;
    private String hora;

    private Materia materia;

    //TODO: Borrar Mock
    public Examen(String sede, String docenteNombre, String docenteApellido, String fecha, String hora) {
        this.sede = new Sede();
        this.sede.setCodigo("1");
        this.sede.setNombre(sede);
        this.docenteACargo = new Persona(docenteNombre, docenteApellido);
        this.curso = new Curso(1, new ArrayList<Horario>() {{add(new Horario("Lunes", "12:00", "15:00", "200"));}}, 30);
        this.curso.setDocenteACargo(docenteACargo);
        this.curso.setSede(this.sede);
        this.setMateria(new Materia("75.01", "Algoritmos y Programación I", "Computación"));
        this.aula = "200";
        this.fecha = fecha;
        this.hora = hora;
        this.oportunidad = "1";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDocente() {
        return docenteACargo.toString();
    }

    public Persona getDocenteACargo() {
        return docenteACargo;
    }

    public void setDocenteACargo(Persona docenteACargo) {
        this.docenteACargo = docenteACargo;
    }

    public String getFecha() {
        String[] data = fecha.split("T");
        String[] datafecha = data[0].split("-");
        return datafecha[2] + "/" + datafecha[1] + "/" + datafecha[0];
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        String[] data = fecha.split("T");
        return data[1].substring(0,5);
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }


    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getOportunidad() {
        return oportunidad;
    }

    public void setOportunidad(String oportunidad) {
        this.oportunidad = oportunidad;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
