package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionCursosListener;

public class InscripcionExamenMateriaViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, ResponseWatcher {
    private TextView textViewCodigo;
    private TextView textViewNombre;
    private Materia materia;

    private ProgressPopup progressPopup;

    public InscripcionExamenMateriaViewHolder(final View itemView) {
        super(itemView);

        textViewCodigo = itemView.findViewById(R.id.codigo_materia);
        textViewNombre = itemView.findViewById(R.id.nombre_materia);

        progressPopup = new ProgressPopup("Cargando fechas de examen...", itemView.getContext());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFechasExamen();
                ((MainActivity) itemView.getContext()).setMateriaSeleccionada(materia);
            }
        });

    }

    private void loadFechasExamen() {
        progressPopup.show();
        Context context = itemView.getContext();
        InscripcionExamenListener inscripcionExamenListener = new InscripcionExamenListener(context, this);

        //TODO: Sacar el mock

        inscripcionExamenListener.onRequestCompleted(null);
        /*RequestSender requestSender = new RequestSender(context);

        String url = context.getString(R.string.urlAppServer) + "examenes/" + materia.get_id();

        requestSender.doGet_expectJSONObject(inscripcionExamenListener, url);*/
    }

    public void bindTo(Materia materia) {

        textViewCodigo.setText(materia.getCodigo());
        textViewNombre.setText(materia.getNombre());
        this.materia = materia;
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
