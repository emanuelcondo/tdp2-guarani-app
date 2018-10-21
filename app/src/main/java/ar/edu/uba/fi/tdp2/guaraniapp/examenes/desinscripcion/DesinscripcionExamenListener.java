package ar.edu.uba.fi.tdp2.guaraniapp.examenes.desinscripcion;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.Examen;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.InscripcionExamen;

public class DesinscripcionExamenListener implements ResponseListener {
    private Context context;

    public DesinscripcionExamenListener(Context context) {
        this.context = context;
    }

    @Override
    public void onRequestCompleted(Object response) {
        RequestHelper.showError(context, "Desinscripción exitosa");
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(context, errorMessage);
    }

}
