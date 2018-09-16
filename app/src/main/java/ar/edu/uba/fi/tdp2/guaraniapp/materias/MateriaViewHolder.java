package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;

public class MateriaViewHolder  extends RecyclerView.ViewHolder
        implements View.OnClickListener  {
    private TextView textViewCodigo;
    private TextView textViewNombre;
    private Materia materia;

    public MateriaViewHolder(final View itemView) {
        super(itemView);

        textViewCodigo = itemView.findViewById(R.id.codigo_materia);
        textViewNombre = itemView.findViewById(R.id.nombre_materia);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CursosFragment cursosFragment = new CursosFragment();
                cursosFragment.setCursos(materia.getCursos());
                FragmentLoader.load((Activity) itemView.getContext(), cursosFragment, FragmentLoader.Cursos);
            }
        });

    }

    public void bindTo(Materia materia) {
        textViewCodigo.setText(materia.getCodigo());
        textViewNombre.setText(materia.getNombre());
        this.materia = materia;
    }

    @Override
    public void onClick(View view) {

    }

}
