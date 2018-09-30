package ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion;

import android.content.Context;

import ar.edu.uba.fi.tdp2.guaraniapp.ResponseParser;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;

public class DesinscripcionCursosListener implements ResponseListener {
    private Context context;
    private DesinscripcionCursosFragment fragment;

    public DesinscripcionCursosListener(Context context, DesinscripcionCursosFragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public void onRequestCompleted(Object response) {
        try {

            fragment.onSuccess(ResponseParser.getInscripciones(response));

        } catch (RuntimeException e) {
            RequestHelper.showError(context, e.getMessage());
            fragment.onError();
        }
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(context, errorMessage);
        fragment.onError();
    }

}
