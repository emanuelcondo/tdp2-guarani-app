package ar.edu.uba.fi.tdp2.guaraniapp.examenes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta.OfertaMateriasListener;

public class FechaExamenViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, ResponseWatcher {
    private TextView textViewCodigo;
    private TextView textViewNombre;
    private FechaExamen fechaExamen;

    private ProgressPopup progressPopup;

    public FechaExamenViewHolder(final View itemView) {
        super(itemView);

        textViewCodigo = itemView.findViewById(R.id.fecha_examen_codigo_materia);
        textViewNombre = itemView.findViewById(R.id.fecha_examen_nombre_materia);

        progressPopup = new ProgressPopup("Cargando fechas de examen...", itemView.getContext());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFechasExamen();
            }
        });

    }

    public void bindTo(FechaExamen fechaExamen) {


        textViewNombre.setText(fechaExamen.getMateria());
        textViewCodigo.setText(String.valueOf((fechaExamen.getCodigoMateria())));
        this.fechaExamen = fechaExamen;
    }

    private void loadFechasExamen() {
        progressPopup.show();
        Context context = itemView.getContext();
        /*
        FechasExamenListener fechasExamenListener = new FechasExamenListener(context, this);
        RequestSender requestSender = new RequestSender(context);

        String url = context.getString(R.string.urlAppServer) + "materias/fechas-examen/";

        requestSender.doGet_expectJSONObject(fechasExamenListener, url);
        */
    }

    public void onSuccess() {
        progressPopup.dismiss();
    }

    public void onError() {
        progressPopup.dismiss();
    }

    @Override
    public void onClick(View view) {

    }

}

