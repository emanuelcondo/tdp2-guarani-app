package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Examen;

public class InscripcionExamenListener implements ResponseListener {
    private Examen examen;

    private InscripcionExamenFragment inscripcionExamenFragment;
    private ProgressPopup progressPopup;

    public InscripcionExamenListener(Examen examen, InscripcionExamenFragment inscripcionExamenFragment) {
        this.examen = examen;
        this.inscripcionExamenFragment = inscripcionExamenFragment;
        progressPopup = new ProgressPopup("Inscribiendo en examen...", inscripcionExamenFragment.getContext());
        progressPopup.show();
    }

    @Override
    public void onRequestCompleted(Object response) {
        ((MainActivity) inscripcionExamenFragment.getActivity()).removeFechaExamen(examen);
        inscripcionExamenFragment.getAdapter().notifyDataSetChanged();
        RequestHelper.showError(inscripcionExamenFragment.getContext(), "Inscripción exitosa");
        progressPopup.dismiss();
    }

    @Override
    public void onRequestError(int codError, String errorMessage) {
        RequestHelper.showError(inscripcionExamenFragment.getContext(), errorMessage);
        progressPopup.dismiss();
    }

}
