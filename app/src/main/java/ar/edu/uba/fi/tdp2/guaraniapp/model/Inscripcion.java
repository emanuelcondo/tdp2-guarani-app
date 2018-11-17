package ar.edu.uba.fi.tdp2.guaraniapp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    @Override
    public boolean equals( Object obj) {
        Inscripcion ins = (Inscripcion)obj;
        return ins.getMateria().equals(materia);
    }

    public String getFormatedTimestamp() {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(getTimestamp());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR, -3);
            date = cal.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return getTimestamp();
        }
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(date);
        return  "Fecha de Inscripción: " + formattedDate;
    }

}
