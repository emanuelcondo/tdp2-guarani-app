package ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Horario;

public class OfertaCursoViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener  {
    private TextView textViewNumeroCurso;
    private TextView textViewDocente;
    private TableLayout tableLayoutHorarios;

    private Curso curso;

    private MainActivity activity;

    public OfertaCursoViewHolder(View itemView, MainActivity activity) {
        super(itemView);

        textViewNumeroCurso = itemView.findViewById(R.id.numero_curso);
        textViewDocente = itemView.findViewById(R.id.docente_curso);
        tableLayoutHorarios = itemView.findViewById(R.id.tabla_horarios);

        this.activity = activity;
    }

    public void bindTo(Curso curso) {
        this.curso = curso;
        bindViews();
    }

    private void bindViews() {
        String numeroCurso = "Curso " + curso.getNumeroCurso();
        textViewNumeroCurso.setText(numeroCurso);
        textViewNumeroCurso.setOnClickListener(this);
        textViewDocente.setText(curso.getDocente());
        textViewDocente.setOnClickListener(this);

        TableRow header = new TableRow(itemView.getContext());
        TextView textViewDias = new TextView(itemView.getContext());
        textViewDias.setText("Días");
        textViewDias.setTextSize(14);
        textViewDias.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewDias.setBackgroundResource(R.color.colorPrimary);
        textViewDias.setPadding(8,8,8,8);

        TextView textViewHorarios = new TextView(itemView.getContext());
        textViewHorarios.setText("Horarios");
        textViewHorarios.setTextSize(14);
        textViewHorarios.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHorarios.setBackgroundResource(R.color.colorPrimary);
        textViewHorarios.setPadding(8,8,8,8);

        TextView textViewHeaderSede = new TextView(itemView.getContext());
        textViewHeaderSede.setText("Sede");
        textViewHeaderSede.setTextSize(14);
        textViewHeaderSede.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHeaderSede.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderSede.setPadding(8,8,8,8);

        TextView textViewHeaderAula = new TextView(itemView.getContext());
        textViewHeaderAula.setText("Aula");
        textViewHeaderAula.setTextSize(14);
        textViewHeaderAula.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHeaderAula.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderAula.setPadding(8,8,8,8);

        header.addView(textViewDias);
        header.addView(textViewHorarios);
        header.addView(textViewHeaderSede);
        header.addView(textViewHeaderAula);

        tableLayoutHorarios.addView(header);
        tableLayoutHorarios.setOnClickListener(this);

        for (Horario horario:curso.getHorarios()) {
            TableRow row = new TableRow(itemView.getContext());

            TextView textViewDia = new TextView(itemView.getContext());
            textViewDia.setText(horario.getDia());
            textViewDia.setBackgroundResource(R.drawable.cell_shape);
            textViewDia.setPadding(8,8,8,8);

            TextView textViewHorario = new TextView(itemView.getContext());
            String s_horario = horario.getHoraInicio() + "hs - " + horario.getHoraFin() + "hs";
            textViewHorario.setText(s_horario);
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

        Log.d("ClicCursoViewHolder", "Clic en curso: " + curso.getDocente());
        activity.setCursoSeleccionado(curso);
        FragmentLoader.load(activity, new OfertaFragment(), FragmentLoader.OfertaCurso);

    }

}
