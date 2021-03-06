package ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Carrera;

public class OfertaCarreraViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, ResponseWatcher {
    private TextView textViewCodigo;
    private TextView textViewNombre;
    private Carrera carrera;

    private ProgressPopup progressPopup;

    public OfertaCarreraViewHolder(final View itemView) {
        super(itemView);

        textViewCodigo = itemView.findViewById(R.id.codigo_carrera);
        textViewNombre = itemView.findViewById(R.id.nombre_carrera);

        progressPopup = new ProgressPopup("Cargando materias...", itemView.getContext());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadMaterias();
            }
        });

    }

    public void bindTo(Carrera carrera) {


        textViewNombre.setText(carrera.getNombre());
        textViewCodigo.setText(String.valueOf((carrera.getCodigo())));
        this.carrera = carrera;
    }

    private void loadMaterias() {
        progressPopup.show();
        Context context = itemView.getContext();
        OfertaMateriasListener ofertaMateriasListener = new OfertaMateriasListener(context, this);
        RequestSender requestSender = new RequestSender(context);

        String url = context.getString(R.string.urlAppServer) + "oferta-academica/materias/carrera/" + carrera.get_id();

        requestSender.doGet_expectJSONObject(ofertaMateriasListener, url);
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

