package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.content.Context;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ConfirmationPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.Examen;

class InscripcionExamenLibreWatcher implements ResponseWatcher {
    private Examen examen;

    private ConfirmationPopup confirmationPopup;
    private Context context;

    public InscripcionExamenLibreWatcher(Examen examen, Context context) {
        this.examen = examen;
        this.context = context;

        confirmationPopup = new ConfirmationPopup(
                String.valueOf((examen.getMateria().getCodigo())) + " " + examen.getMateria().getNombre()
                , examen.getFecha()
                , "¿Desea inscribirse como LIBRE a esta fecha de examen?"
                , "Inscribirse"
                , "Cancelar"
                , this
                , context);
        confirmationPopup.show();
    }

    @Override
    public void onSuccess() {
        confirmationPopup.dismiss();

        InscripcionExamenListener listener = new InscripcionExamenListener(context);
        RequestSender requestSender = new RequestSender(context);

        Map<String,String> parametros;
        parametros = new HashMap<>();
        parametros.put("condicion", context.getString(R.string.anotarse_libre_examen));
        String url = context.getString(R.string.urlAppServer) + "inscripciones/examenes/" + examen.get_id();

        requestSender.doPost(listener, url, new JSONObject(parametros));

    }

    @Override
    public void onError() {
        confirmationPopup.dismiss();
    }
}
