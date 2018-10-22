package ar.edu.uba.fi.tdp2.guaraniapp.examenes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InscripcionExamen {

    private String _id;
    private String timestamp;
    private String condicion;

    //TODO: todos los datos sacarlos de Examen
    private Examen examen;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTimestamp() {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(timestamp);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR, -3);
            date = cal.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(date);
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

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
}

/*
"inscripciones": [
            {
                "timestamp": "2018-10-21T02:59:48.455Z",
                "_id": "5bcbeba4c1259b28df032325",
                "alumno": "5ba5d7a9439b14538ee4c3a5",

                "condicion": "Regular",
                "__v": 0
            },
            {
                "timestamp": "2018-10-21T03:00:06.163Z",
                "_id": "5bcbebb6c1259b28df032326",
                "alumno": "5ba5d7a9439b14538ee4c3a5",
                "examen": {
                    "aula": null,
                    "_id": "5bcbdc192a7bcac5412a5f29",
                    "curso": "5ba718b71dabf8854f11e17e",
                    "materia": "5ba6cdae8b7931ac3e21ddd6",
                    "fecha": "2018-12-04T12:00:00.000Z",
                    "__v": 0
                },
                "condicion": "Regular",
                "__v": 0
            }
        ]

 */