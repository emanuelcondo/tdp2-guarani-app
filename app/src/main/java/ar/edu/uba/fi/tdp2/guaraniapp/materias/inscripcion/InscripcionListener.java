package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

import android.content.Context;

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;

public class InscripcionListener implements ResponseListener {
    private Context context;

    public InscripcionListener(Context context) {
        this.context = context;
    }

    @Override
    public void onRequestCompleted(Object response) {
        RequestHelper.showError(context, "Inscripción exitosa, ver si fue condicional o regular");
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {

        RequestHelper.showError(context, errorMessage);
    }
}
