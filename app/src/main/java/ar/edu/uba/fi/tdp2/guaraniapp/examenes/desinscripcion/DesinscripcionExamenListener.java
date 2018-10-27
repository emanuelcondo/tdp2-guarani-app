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
    private DesinscripcionExamenFragment desinscripcionExamenFragment;
    private InscripcionExamen inscripcionExamen;

    public DesinscripcionExamenListener(DesinscripcionExamenFragment desinscripcionExamenFragment, InscripcionExamen inscripcionExamen) {
        this.desinscripcionExamenFragment = desinscripcionExamenFragment;
        this.inscripcionExamen = inscripcionExamen;
    }

    @Override
    public void onRequestCompleted(Object response) {
        RequestHelper.showError(desinscripcionExamenFragment.getContext(), "Desinscripción exitosa");
        desinscripcionExamenFragment.onResume();
        desinscripcionExamenFragment.progressPopup.dismiss();
        ((MainActivity) desinscripcionExamenFragment.getActivity()).removeInscripcionExamen(inscripcionExamen);
        ((MainActivity) desinscripcionExamenFragment.getActivity()).flipDesinscripcionExamenes();
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(desinscripcionExamenFragment.getContext(), errorMessage);
    }

}
