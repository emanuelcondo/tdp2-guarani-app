package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ConfirmationPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.Examen;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionListener;

public class InscripcionExamenViewHolder extends RecyclerView.ViewHolder implements ResponseWatcher {
    private TextView textViewCodigo;
    private TextView textViewNombre;
    //private TextView textViewOportunidad;
    //private TextView textViewDocente;
    private TableLayout tableHorarioExamen;
    private Examen examen;

    private ConfirmationPopup confirmationPopup;

    public InscripcionExamenViewHolder(final View itemView) {
        super(itemView);

        textViewCodigo = itemView.findViewById(R.id.fecha_examen_codigo_curso);
        textViewNombre = itemView.findViewById(R.id.fecha_examen_nombre_curso);
        //textViewOportunidad = itemView.findViewById(R.id.fecha_examen_oportunidad);
        //textViewDocente = itemView.findViewById(R.id.fecha_examen_docente);
        tableHorarioExamen = itemView.findViewById(R.id.tabla_horario_examen);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmationPopup.show();
            }
        });
    }

    public void bindTo(Examen examen) {

        Curso curso = examen.getCurso();
        Materia materia = examen.getMateria();

        /*
        textViewNombre.setText(materia.getNombre());
        textViewCodigo.setText(String.valueOf((materia.getCodigo())));
        */
        textViewNombre.setText(curso.getDocente());
        textViewCodigo.setText(itemView.getContext().getString(R.string.curso_header, curso.getComision()));
        //getString(R.string.curso_header, curso.getComision())
        //textViewOportunidad.setText(examen.getOportunidad());
        //textViewDocente.setText(curso.getDocente());
        this.examen = examen;

        confirmationPopup = new ConfirmationPopup(
                String.valueOf((materia.getCodigo())) + " " + materia.getNombre()
                , examen.getFecha() + " " + examen.getHora()
                , "¿Desea inscribirse a esta fecha de examen?"
                , "Inscribirse"
                , "Cancelar"
                , this
                , itemView.getContext());

        TableRow header = new TableRow(itemView.getContext());
        TextView textViewFecha = new TextView(itemView.getContext());
        textViewFecha.setText(R.string.horario_examen_header);
        textViewFecha.setTextSize(16);
        textViewFecha.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewFecha.setBackgroundResource(R.color.colorPrimary);
        textViewFecha.setPadding(8,8,8,8);

        /*
        TextView textViewHeaderSede = new TextView(itemView.getContext());
        textViewHeaderSede.setText(R.string.sede_header);
        textViewHeaderSede.setTextSize(14);
        textViewHeaderSede.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHeaderSede.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderSede.setPadding(8,8,8,8);
        */

        TextView textViewHeaderHora = new TextView(itemView.getContext());
        textViewHeaderHora.setText(R.string.horario_header);
        textViewHeaderHora.setTextSize(16);
        textViewHeaderHora.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHeaderHora.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderHora.setPadding(8,8,8,8);

        header.addView(textViewFecha);
        //header.addView(textViewHeaderSede);
        header.addView(textViewHeaderHora);

        tableHorarioExamen.addView(header);

        TableRow row = new TableRow(itemView.getContext());

        TextView textViewFechaRow = new TextView(itemView.getContext());
        textViewFechaRow.setText(examen.getFecha());
        textViewFechaRow.setBackgroundResource(R.drawable.cell_shape);
        textViewFechaRow.setPadding(8,8,8,8);

        /*
        TextView textViewSede = new TextView(itemView.getContext());
        textViewSede.setText(curso.getSede().getNombre());
        textViewSede.setBackgroundResource(R.drawable.cell_shape);
        textViewSede.setPadding(8,8,8,8);
        */

        TextView textViewHoraRow = new TextView(itemView.getContext());
        textViewHoraRow.setText(examen.getHora());
        textViewHoraRow.setBackgroundResource(R.drawable.cell_shape);
        textViewHoraRow.setPadding(8,8,8,8);

        row.addView(textViewFechaRow);
        //row.addView(textViewSede);
        row.addView(textViewHoraRow);

        tableHorarioExamen.addView(row);
    }

    @Override
    public void onSuccess() {

        confirmationPopup.dismiss();

        InscripcionExamenListener listener = new InscripcionExamenListener(itemView.getContext());
        RequestSender requestSender = new RequestSender(itemView.getContext());

        String url = itemView.getContext().getString(R.string.urlAppServer) + "inscripciones/examenes/" + examen.get_id();

        requestSender.doPost(listener, url, new JSONObject());

    }

    @Override
    public void onError() {
        confirmationPopup.dismiss();
    }
}

