package ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Carrera;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionMateriasListener;

public class OfertaCarreraViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener  {
    private TextView textViewCodigo;
    private TextView textViewNombre;
    private Carrera carrera;

    public OfertaCarreraViewHolder(final View itemView) {
        super(itemView);

        textViewCodigo = itemView.findViewById(R.id.codigo_carrera);
        textViewNombre = itemView.findViewById(R.id.nombre_carrera);

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
        Context context = itemView.getContext();
        InscripcionMateriasListener inscripcionMateriasListener = new InscripcionMateriasListener(context);
        RequestSender requestSender = new RequestSender(context);

        String url = context.getString(R.string.urlAppServer) + "materias/carrera/" + carrera.get_id();

        requestSender.doGet_expectJSONObject(inscripcionMateriasListener, url);
    }

    @Override
    public void onClick(View view) {

    }

}

