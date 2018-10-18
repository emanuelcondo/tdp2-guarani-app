package ar.edu.uba.fi.tdp2.guaraniapp.examenes;

public class InscripcionExamen {

    private String _id;
    private String timestamp;

    //TODO: todos los datos sacarlos de Examen
    private Examen examen;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
}
