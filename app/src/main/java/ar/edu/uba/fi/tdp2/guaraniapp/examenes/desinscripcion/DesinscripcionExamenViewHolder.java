package ar.edu.uba.fi.tdp2.guaraniapp.examenes.desinscripcion;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ConfirmationPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Examen;
import ar.edu.uba.fi.tdp2.guaraniapp.model.InscripcionExamen;

public class DesinscripcionExamenViewHolder extends RecyclerView.ViewHolder
        implements ResponseWatcher {
    private TextView textViewCodigoMateria;
    private TextView textViewNombreMateria;
    private TextView textViewNumeroCurso;
    private TextView textViewDocente;
    private TableLayout tableHorarioExamen;
    private TextView textViewFechaInscripcion;

    private InscripcionExamen inscripcionExamen;

    private ConfirmationPopup confirmationPopup;

    private MainActivity activity;
    private DesinscripcionExamenFragment desinscripcionExamenFragment;

    public DesinscripcionExamenViewHolder(View itemView, MainActivity activity, DesinscripcionExamenFragment desinscripcionExamenFragment) {
        super(itemView);

        textViewCodigoMateria = itemView.findViewById(R.id.inscripcion_examen_codigo_materia);
        textViewNombreMateria = itemView.findViewById(R.id.inscripcion_examen_nombre_materia);
        textViewNumeroCurso = itemView.findViewById(R.id.inscripcion_examen_numero_curso);
        textViewDocente = itemView.findViewById(R.id.inscripcion_examen_docente);
        tableHorarioExamen = itemView.findViewById(R.id.tabla_horario_inscripcion_examen);
        textViewFechaInscripcion = itemView.findViewById(R.id.fecha_inscripcion_examen);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmationPopup.show();
            }
        });

        this.activity = activity;
        this.desinscripcionExamenFragment = desinscripcionExamenFragment;
    }

    public void bindTo(InscripcionExamen inscripcionExamen) {
        this.inscripcionExamen = inscripcionExamen;
        bindViews();
    }

    private void bindViews() {

        Examen examen = inscripcionExamen.getExamen();

        textViewNombreMateria.setText(examen.getMateria().getNombre());
        textViewCodigoMateria.setText(String.valueOf((examen.getMateria().getCodigo())));
        textViewNumeroCurso.setText(itemView.getContext().getString(R.string.curso_header, examen.getCurso().getComision()));
        textViewDocente.setText(examen.getCurso().getDocente());

        confirmationPopup = new ConfirmationPopup(
                String.valueOf((examen.getMateria().getCodigo())) + " " + examen.getMateria().getNombre()
                , examen.getFecha()
                , "¿Desea desinscribirse a esta fecha de examen?"
                , "Desinscribirse"
                , "Cancelar"
                , this
                , itemView.getContext());

        tableHorarioExamen.removeAllViews();

        TableRow header = new TableRow(itemView.getContext());

        TextView textViewHeaderFecha = new TextView(itemView.getContext());
        textViewHeaderFecha.setText(R.string.horario_examen_header);
        textViewHeaderFecha.setTextSize(14);
        textViewHeaderFecha.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHeaderFecha.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderFecha.setPadding(8,8,8,8);

        header.addView(textViewHeaderFecha);

        tableHorarioExamen.addView(header);

        TableRow row = new TableRow(itemView.getContext());

        TextView textViewFecha = new TextView(itemView.getContext());
        textViewFecha.setText(examen.getFecha());
        textViewFecha.setBackgroundResource(R.drawable.cell_shape);
        textViewFecha.setPadding(8,8,8,8);

        row.addView(textViewFecha);

        tableHorarioExamen.addView(row);

        String fechaInscripcion = "Fecha de Inscripción: " + inscripcionExamen.getTimestamp();
        textViewFechaInscripcion.setText(fechaInscripcion);

    }

    private void desinscribir() {
        desinscripcionExamenFragment.progressPopup.show();
        DesinscripcionExamenListener listener = new DesinscripcionExamenListener(desinscripcionExamenFragment, inscripcionExamen);
        RequestSender requestSender = new RequestSender(activity);

        String url = activity.getString(R.string.urlAppServer) + "inscripciones/" + inscripcionExamen.get_id() + "/examenes";

        requestSender.doDelete(listener, url);
    }

    @Override
    public void onSuccess() {
        desinscribir();
        confirmationPopup.dismiss();
    }

    @Override
    public void onError() {
        confirmationPopup.dismiss();
    }

}
