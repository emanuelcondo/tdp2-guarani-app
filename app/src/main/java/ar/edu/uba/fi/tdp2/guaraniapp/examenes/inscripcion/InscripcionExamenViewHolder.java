package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ConfirmationPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.FechaExamen;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;

public class InscripcionExamenViewHolder extends RecyclerView.ViewHolder implements ResponseWatcher {
    private TextView textViewCodigo;
    private TextView textViewNombre;
    private TextView textViewOportunidad;
    private TextView textViewDocente;
    private TableLayout tableHorarioExamen;
    private FechaExamen fechaExamen;

    private ConfirmationPopup confirmationPopup;

    public InscripcionExamenViewHolder(final View itemView) {
        super(itemView);

        textViewCodigo = itemView.findViewById(R.id.fecha_examen_codigo_materia);
        textViewNombre = itemView.findViewById(R.id.fecha_examen_nombre_materia);
        textViewOportunidad = itemView.findViewById(R.id.fecha_examen_oportunidad);
        textViewDocente = itemView.findViewById(R.id.fecha_examen_docente);
        tableHorarioExamen = itemView.findViewById(R.id.tabla_horario_examen);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmationPopup.show();
            }
        });
    }

    public void bindTo(FechaExamen fechaExamen) {

        Curso curso = fechaExamen.getCurso();
        Materia materia = fechaExamen.getMateria();

        textViewNombre.setText(materia.getNombre());
        textViewCodigo.setText(String.valueOf((materia.getCodigo())));
        textViewOportunidad.setText(fechaExamen.getOportunidad());
        textViewDocente.setText(curso.getDocente());
        this.fechaExamen = fechaExamen;

        confirmationPopup = new ConfirmationPopup(
                String.valueOf((materia.getCodigo())) + " " + materia.getNombre()
                , fechaExamen.getFecha() + " " + fechaExamen.getHora()
                , "¿Desea inscribirse a esta fecha de examen?"
                , "Inscribirse"
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
        textViewFecha.setText(fechaExamen.getFecha() + ", " + fechaExamen.getHora());
        textViewFecha.setBackgroundResource(R.drawable.cell_shape);
        textViewFecha.setPadding(8,8,8,8);

        TextView textViewSede = new TextView(itemView.getContext());
        textViewSede.setText(curso.getSede().getNombre());
        textViewSede.setBackgroundResource(R.drawable.cell_shape);
        textViewSede.setPadding(8,8,8,8);

        TextView textViewAula = new TextView(itemView.getContext());
        textViewAula.setText(fechaExamen.getAula());
        textViewAula.setBackgroundResource(R.drawable.cell_shape);
        textViewAula.setPadding(8,8,8,8);

        row.addView(textViewFecha);
        row.addView(textViewSede);
        row.addView(textViewAula);

        tableHorarioExamen.addView(row);
    }

    @Override
    public void onSuccess() {
        //TODO: Inscribir examen en el server
        confirmationPopup.dismiss();
        Toast.makeText(itemView.getContext(), "Inscripcion de examen", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError() {
        confirmationPopup.dismiss();
    }
}

