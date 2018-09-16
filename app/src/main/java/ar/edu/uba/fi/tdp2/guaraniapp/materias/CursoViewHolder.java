package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.R;

public class CursoViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener  {
    private TextView textViewNumeroCurso;
    private TextView textViewDocente;
    private TableLayout tableLayoutHorarios;

    public CursoViewHolder(View itemView) {
        super(itemView);

        textViewNumeroCurso = itemView.findViewById(R.id.numero_curso);
        textViewDocente = itemView.findViewById(R.id.docente_curso);
        tableLayoutHorarios = itemView.findViewById(R.id.tabla_horarios);
    }

    public void bindTo(Curso curso) {
        String numeroCurso = "Curso " + curso.getNumeroCurso();
        textViewNumeroCurso.setText(numeroCurso);
        textViewDocente.setText(curso.getDocente());

        TableRow header = new TableRow(itemView.getContext());
        TextView textViewDias = new TextView(itemView.getContext());
        textViewDias.setText("Días");
        textViewDias.setTextSize(14);
        textViewDias.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimaryDark));
        textViewDias.setBackgroundResource(R.drawable.cell_shape);
        textViewDias.setPadding(8,8,8,8);
        TextView textViewHorarios = new TextView(itemView.getContext());
        textViewHorarios.setText("Horarios");
        textViewHorarios.setTextSize(14);
        textViewHorarios.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimaryDark));
        textViewHorarios.setBackgroundResource(R.drawable.cell_shape);
        textViewHorarios.setPadding(8,8,8,8);
        header.addView(textViewDias);
        header.addView(textViewHorarios);

        tableLayoutHorarios.addView(header);

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
            row.addView(textViewDia);
            row.addView(textViewHorario);

            tableLayoutHorarios.addView(row);
        }
    }

    @Override
    public void onClick(View view) {

    }

}
