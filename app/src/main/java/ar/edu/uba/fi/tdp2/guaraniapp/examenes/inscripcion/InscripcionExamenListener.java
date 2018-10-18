package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.ResponseParser;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.Examen;

public class InscripcionExamenListener implements ResponseListener {
    private Context context;
    private ResponseWatcher watcher;

    public InscripcionExamenListener(Context context, ResponseWatcher watcher) {
        this.context = context;
        this.watcher = watcher;
    }

    @Override
    public void onRequestCompleted(Object response) {
        try {

            //TODO: Borrar Mock

            ((MainActivity)context).setFechasExamen(ResponseParser.getFechasExamen(response));

            //List<Examen> fechasDeExamen = new ArrayList<Examen>(){{add(new Examen("Paseo Colón", "Adrián", "Bastía", "20/10/2018", "13:30"));}};
            //((MainActivity)context).setFechasExamen(fechasDeExamen);
            FragmentLoader.load((Activity) context, new InscripcionExamenFragment(), "InscripcionExamenes");

            watcher.onSuccess();

        } catch (RuntimeException e) {
            RequestHelper.showError(context, e.getMessage());
            watcher.onError();
        }
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(context, errorMessage);
        watcher.onError();
    }

}
