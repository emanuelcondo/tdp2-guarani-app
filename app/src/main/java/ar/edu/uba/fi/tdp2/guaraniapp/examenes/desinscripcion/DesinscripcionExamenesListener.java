package ar.edu.uba.fi.tdp2.guaraniapp.examenes.desinscripcion;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.ResponseParser;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.Examen;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.InscripcionExamen;

public class DesinscripcionExamenesListener implements ResponseListener {
    private Context context;
    private ProgressPopup progressPopup;
    private ResponseWatcher watcher;

    public DesinscripcionExamenesListener(Context context, ResponseWatcher watcher) {
        this.context = context;
        this.watcher = watcher;
        progressPopup = new ProgressPopup("Cargando inscripciones a examen...", context);
        progressPopup.show();
    }

    @Override
    public void onRequestCompleted(Object response) {
        try {

            ((MainActivity)context).getAlumno().setInscripcionesExamenes(ResponseParser.getInscripcionesExamenes(response));

            watcher.onSuccess();

        } catch (RuntimeException e) {
            RequestHelper.showError(context, e.getMessage());
            watcher.onError();
        }
        progressPopup.dismiss();
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(context, errorMessage);
        progressPopup.dismiss();
        watcher.onError();
    }

}
