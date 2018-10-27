package ar.edu.uba.fi.tdp2.guaraniapp.model;

public class Persona {
    private String _id;
    private String nombre;
    private String apellido;

    public Persona(String nombre, String apellido) {
        setApellido(apellido);
        setNombre(nombre);
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return apellido + ", " + nombre;
    }
}
