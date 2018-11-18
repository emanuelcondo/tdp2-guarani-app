package ar.edu.uba.fi.tdp2.guaraniapp.encuestas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.EncuestaCurso;

public class EncuestasCursoViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewCodigoMateria;
    private TextView textViewNombreMateria;
    private TextView textViewNumeroCurso;
    private TextView textViewDocente;
    private Button responderEncuesta;

    private EncuestaCurso encuestaCurso;

    public EncuestasCursoViewHolder(View itemView, final MainActivity activity) {
        super(itemView);

        textViewCodigoMateria = itemView.findViewById(R.id.codigo_materia);
        textViewNombreMateria = itemView.findViewById(R.id.nombre_materia);
        textViewNumeroCurso = itemView.findViewById(R.id.numero_curso);
        textViewDocente = itemView.findViewById(R.id.docente_curso);
        responderEncuesta = itemView.findViewById(R.id.responder_encuesta);

        responderEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.setEncuestaCursoSeleccionada(encuestaCurso);
                FragmentLoader.load(activity, new SlidingTabsBasicFragment(), FragmentLoader.Encuestas);
            }
        });
    }

    public void bindTo(EncuestaCurso encuestaCurso) {
        this.encuestaCurso = encuestaCurso;
        bindViews();
    }

    private void bindViews() {

        Curso curso;
        curso = encuestaCurso.getCurso();
        textViewDocente.setText(curso.getDocente());
        textViewCodigoMateria.setText(encuestaCurso.getMateria().getCodigo());
        textViewNombreMateria.setText(encuestaCurso.getMateria().getNombre());

        textViewNumeroCurso.setText(itemView.getContext().getString(R.string.curso_header, curso.getComision()));
    }

}
