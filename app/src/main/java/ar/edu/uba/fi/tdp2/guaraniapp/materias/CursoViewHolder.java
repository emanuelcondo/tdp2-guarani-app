package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.R;

public class CursoViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener  {
    private TextView textViewDocente;
    private TextView textViewHorario;

    public CursoViewHolder(View itemView) {
        super(itemView);

        textViewDocente = itemView.findViewById(R.id.docente_curso);
        textViewHorario = itemView.findViewById(R.id.horario_curso);
    }

    public void bindTo(Curso curso) {
        textViewDocente.setText(curso.getDocente());
        textViewHorario.setText(curso.getHorario());
    }

    @Override
    public void onClick(View view) {

    }

}
