package ar.edu.uba.fi.tdp2.guaraniapp.encuestas;

import android.content.Context;

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;

public class EncuestaListener implements ResponseListener {
    private Context context;
    private ResponseWatcher watcher;
    private ProgressPopup progressPopup;

    public EncuestaListener(Context context, ResponseWatcher watcher) {
        this.context = context;
        this.watcher = watcher;
        this.progressPopup = new ProgressPopup("Enviando encuesta...", context);
        this.progressPopup.show();
    }

    @Override
    public void onRequestCompleted(Object response) {
        RequestHelper.showError(context, "La encuesta se envió exitosamente.");
        progressPopup.dismiss();
        watcher.onSuccess();
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(context, errorMessage);
        progressPopup.dismiss();
        watcher.onError();
    }
}
