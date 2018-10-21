package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.content.Context;

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;

public class InscripcionExamenListener implements ResponseListener {
    private Context context;

    public InscripcionExamenListener(Context context) {
        this.context = context;
    }

    @Override
    public void onRequestCompleted(Object response) {
        RequestHelper.showError(context, "Inscripción exitosa");
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(context, errorMessage);
    }

}
