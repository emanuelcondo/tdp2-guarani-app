package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.content.Context;

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;

public class InscripcionExamenListener implements ResponseListener {
    private Context context;
    private ProgressPopup progressPopup;

    public InscripcionExamenListener(Context context) {
        this.context = context;
        progressPopup = new ProgressPopup("Inscripción en curso...", context);
    }

    @Override
    public void onRequestCompleted(Object response) {
        RequestHelper.showError(context, "Inscripción exitosa");
        progressPopup.dismiss();
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(context, errorMessage);
        progressPopup.dismiss();
    }

}
