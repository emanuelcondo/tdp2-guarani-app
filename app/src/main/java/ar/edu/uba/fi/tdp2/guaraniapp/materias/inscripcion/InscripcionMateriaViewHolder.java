package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Estudiante;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionCursosFragment;

public class InscripcionMateriaViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener  {
    private TextView textViewCodigo;
    private TextView textViewNombre;
    private Materia materia;
    private Estudiante usuario;

    public InscripcionMateriaViewHolder(final View itemView) {
        super(itemView);

        textViewCodigo = itemView.findViewById(R.id.codigo_materia);
        textViewNombre = itemView.findViewById(R.id.nombre_materia);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InscripcionCursosFragment inscripcionCursosFragment = new InscripcionCursosFragment();
                inscripcionCursosFragment.setMateria(materia);
                FragmentLoader.load((Activity) itemView.getContext(), inscripcionCursosFragment, FragmentLoader.Cursos);
            }
        });

    }

    public void bindTo(Materia materia, Estudiante usuario) {

        textViewCodigo.setText(materia.getCodigo());
        textViewNombre.setText(materia.getNombre());
        this.materia = materia;
        this.usuario = usuario;
    }

    @Override
    public void onClick(View view) {

    }

}
