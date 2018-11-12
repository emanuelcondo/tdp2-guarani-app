package ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Inscripcion;

public class DesinscripcionCursoViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener  {
    private TextView textViewCodigoMateria;
    private TextView textViewNombreMateria;
    private TextView textViewNumeroCurso;
    private TextView textViewDocente;
    private TableLayout tableLayoutHorarios;
    private TextView textViewFechaInscripcion;

    private Inscripcion inscripcion;

    private MainActivity activity;

    public DesinscripcionCursoViewHolder(View itemView, MainActivity activity) {
        super(itemView);

        textViewCodigoMateria = itemView.findViewById(R.id.codigo_materia);
        textViewNombreMateria = itemView.findViewById(R.id.nombre_materia);
        textViewNumeroCurso = itemView.findViewById(R.id.numero_curso);
        textViewDocente = itemView.findViewById(R.id.docente_curso);
        tableLayoutHorarios = itemView.findViewById(R.id.tabla_horarios);
        textViewFechaInscripcion = itemView.findViewById(R.id.fecha_inscripcion);

        textViewCodigoMateria.setOnClickListener(this);
        textViewDocente.setOnClickListener(this);
        textViewNombreMateria.setOnClickListener(this);
        textViewFechaInscripcion.setOnClickListener(this);
        textViewNumeroCurso.setOnClickListener(this);
        tableLayoutHorarios.setOnClickListener(this);

        itemView.setOnClickListener(this);

        this.activity = activity;
    }

    public void bindTo(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
        bindViews();
    }

    private void bindViews() {

        Curso curso;
        if (inscripcion.esCondicional()) {
            curso = new Curso();
            tableLayoutHorarios.setVisibility(View.GONE);
            textViewDocente.setText(R.string.condicional);
        } else {
            curso = inscripcion.getCurso();
            textViewDocente.setText(curso.getDocente());
        }


        textViewCodigoMateria.setText(inscripcion.getMateria().getCodigo());

        textViewNombreMateria.setText(inscripcion.getMateria().getNombre());

//        Date date = null;
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(inscripcion.getTimestamp());
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(date);
//            cal.add(Calendar.HOUR, -3);
//            date = cal.getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String formattedDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(date);
//        String fechaInscripcion = "Fecha de Inscripción: " + inscripcion.getFormatedTimestamp();
        textViewFechaInscripcion.setText(inscripcion.getFormatedTimestamp());

        textViewNumeroCurso.setText(itemView.getContext().getString(R.string.curso_header, curso.getComision()));

        tableLayoutHorarios.removeAllViews();

        TableRow header = new TableRow(itemView.getContext());
        TextView textViewDias = new TextView(itemView.getContext());
        textViewDias.setText(R.string.dias_header);
        textViewDias.setTextSize(14);
        textViewDias.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewDias.setBackgroundResource(R.color.colorPrimary);
        textViewDias.setPadding(8,8,8,8);

        TextView textViewHorarios = new TextView(itemView.getContext());
        textViewHorarios.setText(R.string.horarios_header);
        textViewHorarios.setTextSize(14);
        textViewHorarios.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHorarios.setBackgroundResource(R.color.colorPrimary);
        textViewHorarios.setPadding(8,8,8,8);

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

        header.addView(textViewDias);
        header.addView(textViewHorarios);
        header.addView(textViewHeaderSede);
        header.addView(textViewHeaderAula);

        tableLayoutHorarios.addView(header);

        for (Horario horario:curso.getCursada()) {
            TableRow row = new TableRow(itemView.getContext());

            TextView textViewDia = new TextView(itemView.getContext());
            textViewDia.setText(horario.getDia());
            textViewDia.setBackgroundResource(R.drawable.cell_shape);
            textViewDia.setPadding(8,8,8,8);

            TextView textViewHorario = new TextView(itemView.getContext());
            textViewHorario.setText(itemView.getContext().getString(R.string.horario_row, horario.getHoraInicio(), horario.getHoraFin()));
            textViewHorario.setBackgroundResource(R.drawable.cell_shape);
            textViewHorario.setPadding(8,8,8,8);

            TextView textViewSede = new TextView(itemView.getContext());
            textViewSede.setText(horario.getSede());
            textViewSede.setBackgroundResource(R.drawable.cell_shape);
            textViewSede.setPadding(8,8,8,8);

            TextView textViewAula = new TextView(itemView.getContext());
            textViewAula.setText(horario.getAula());
            textViewAula.setBackgroundResource(R.drawable.cell_shape);
            textViewAula.setPadding(8,8,8,8);

            row.addView(textViewDia);
            row.addView(textViewHorario);
            row.addView(textViewSede);
            row.addView(textViewAula);

            tableLayoutHorarios.addView(row);
        }

    }

    @Override
    public void onClick(View view) {

        if (inscripcion.esCondicional()) {
            Log.d("ClicCursoViewHolder", "Clic en curso de condicionales");
        } else {
            Log.d("ClicCursoViewHolder", "Clic en curso: " + inscripcion.getCurso().getDocente());
        }
        activity.setInscripcionSeleccionada(inscripcion);
        FragmentLoader.load(activity, new DesinscripcionFragment(), FragmentLoader.DesinscripcionCurso);
    }

}
