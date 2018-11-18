package ar.edu.uba.fi.tdp2.guaraniapp.encuestas;

import android.content.Context;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.ResponseParser;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;

public class EncuestasCursosListener implements ResponseListener {
    private Context context;
    private ProgressPopup progressPopup;
    private ResponseWatcher watcher;

    public EncuestasCursosListener(Context context, ResponseWatcher watcher) {
        this.context = context;
        this.watcher = watcher;
        progressPopup = new ProgressPopup("Cargando encuestas...", context);
        progressPopup.show();
    }

    @Override
    public void onRequestCompleted(Object response) {
        try {

            ((MainActivity)context).setEncuestasCursos(ResponseParser.getEncuestaCursos(response));
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
