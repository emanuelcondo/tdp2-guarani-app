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
        RequestHelper.showError(context, "Inscripción exitosa, ver si fue condicional o regular");
        watcher.onSuccess();
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(context, errorMessage);
        watcher.onError();
    }
}
