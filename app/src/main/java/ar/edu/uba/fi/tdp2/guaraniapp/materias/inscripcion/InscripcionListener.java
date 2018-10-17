package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

import android.content.Context;

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;

public class InscripcionListener implements ResponseListener {
    private Context context;
    private ResponseWatcher watcher;

    public InscripcionListener(Context context, ResponseWatcher watcher) {
        this.context = context;
        this.watcher = watcher;
    }

    @Override
    public void onRequestCompleted(Object response) {
        RequestHelper.showError(context, "Inscripción exitosa");
        watcher.onSuccess();
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        if (codError == 400) {
            errorMessage = "Usted ya cuenta con una inscripción a esta materia";
        }
        RequestHelper.showError(context, errorMessage);
        watcher.onError();
    }
}
