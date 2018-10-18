package ar.edu.uba.fi.tdp2.guaraniapp.examenes.desinscripcion;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ConfirmationPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.InscripcionExamen;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;

public class DesinscripcionExamenViewHolder extends RecyclerView.ViewHolder
        implements ResponseWatcher {
    private TextView textViewCodigoMateria;
    private TextView textViewNombreMateria;
    private TextView textViewOportunidad;
    private TextView textViewDocente;
    private TableLayout tableHorarioExamen;
    private TextView textViewFechaInscripcion;

    private InscripcionExamen inscripcionExamen;

    private ConfirmationPopup confirmationPopup;

    private MainActivity activity;

    public DesinscripcionExamenViewHolder(View itemView, MainActivity activity) {
        super(itemView);

        textViewCodigoMateria = itemView.findViewById(R.id.inscripcion_examen_codigo_materia);
        textViewNombreMateria = itemView.findViewById(R.id.inscripcion_examen_nombre_materia);
        textViewOportunidad = itemView.findViewById(R.id.inscripcion_examen_oportunidad);
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
    }

    public void bindTo(InscripcionExamen inscripcionExamen) {
        this.inscripcionExamen = inscripcionExamen;
        bindViews();
    }

    private void bindViews() {

        Curso curso = inscripcionExamen.getFechaExamen().getCurso();

        textViewNombreMateria.setText(curso.getMateria().getNombre());
        textViewCodigoMateria.setText(String.valueOf((curso.getMateria().getCodigo())));
        textViewOportunidad.setText(inscripcionExamen.getFechaExamen().getOportunidad());
        textViewDocente.setText(curso.getDocente());

        confirmationPopup = new ConfirmationPopup(
                String.valueOf((curso.getMateria().getCodigo())) + " " + curso.getMateria().getNombre()
                , inscripcionExamen.getFechaExamen().getFecha() + " " + inscripcionExamen.getFechaExamen().getHora()
                , "¿Desea desinscribirse a esta fecha de examen?"
                , "Desinscribirse"
                , "Cancelar"
                , this
                , itemView.getContext());

        TableRow header = new TableRow(itemView.getContext());
        TextView textViewHorario = new TextView(itemView.getContext());
        textViewHorario.setText(R.string.horario_examen_header);
        textViewHorario.setTextSize(14);
        textViewHorario.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHorario.setBackgroundResource(R.color.colorPrimary);
        textViewHorario.setPadding(8,8,8,8);

        TextView textViewHeaderSede = new TextView(itemView.getContext());
        textViewHeaderSede.setText(R.string.sede_header);
        textViewHeaderSede.setTextSize(14);
        textViewHeaderSede.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHeaderSede.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderSede.setPadding(8,8,8,8);

        TextView textViewHeaderAula = new TextView(itemView.getContext());
        textViewHeaderAula.setText(R.string.aula_header);
        textViewHeaderAula.setTextSize(14);
        textViewHeaderAula.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHeaderAula.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderAula.setPadding(8,8,8,8);

        header.addView(textViewHorario);
        header.addView(textViewHeaderSede);
        header.addView(textViewHeaderAula);

        tableHorarioExamen.addView(header);

        TableRow row = new TableRow(itemView.getContext());

        TextView textViewFecha = new TextView(itemView.getContext());
        textViewFecha.setText(inscripcionExamen.getFechaExamen().getFecha() + ", " + inscripcionExamen.getFechaExamen().getHora());
        textViewFecha.setBackgroundResource(R.drawable.cell_shape);
        textViewFecha.setPadding(8,8,8,8);

        TextView textViewSede = new TextView(itemView.getContext());
        textViewSede.setText(curso.getSede().getNombre());
        textViewSede.setBackgroundResource(R.drawable.cell_shape);
        textViewSede.setPadding(8,8,8,8);

        TextView textViewAula = new TextView(itemView.getContext());
        textViewAula.setText(inscripcionExamen.getFechaExamen().getAula());
        textViewAula.setBackgroundResource(R.drawable.cell_shape);
        textViewAula.setPadding(8,8,8,8);

        row.addView(textViewFecha);
        row.addView(textViewSede);
        row.addView(textViewAula);

        tableHorarioExamen.addView(row);
        String fechaInscripcion = "Fecha de Inscripción: " + inscripcionExamen.getTimestamp();
        textViewFechaInscripcion.setText(fechaInscripcion);

    }

    @Override
    public void onSuccess() {
        //TODO: Desinscribirse en el server
        Toast.makeText(activity, "Desinscripcion de examen", Toast.LENGTH_LONG).show();
        confirmationPopup.dismiss();
    }

    @Override
    public void onError() {
        confirmationPopup.dismiss();
    }
}
