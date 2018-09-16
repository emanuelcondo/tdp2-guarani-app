package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;

public class MateriaViewHolder  extends RecyclerView.ViewHolder
        implements View.OnClickListener  {
    private ImageView imageViewDepartamento;
    private TextView textViewCodigo;
    private TextView textViewNombre;
    private Materia materia;

    public MateriaViewHolder(final View itemView) {
        super(itemView);

        imageViewDepartamento = itemView.findViewById(R.id.imagen_departamento);
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

        switch (materia.getDepartamento()) {
            case "Computacion":
                Glide
                    .with(itemView.getContext())
                    .load(R.drawable.computacion)
                    .into(imageViewDepartamento);
                break;
            case "Gestion":
                Glide
                        .with(itemView.getContext())
                        .load(R.drawable.gestion)
                        .into(imageViewDepartamento);
                break;
            default:
                Glide
                        .with(itemView.getContext())
                        .load(R.drawable.computacion)
                        .into(imageViewDepartamento);
                break;
        }

        textViewCodigo.setText(materia.getCodigo());
        textViewNombre.setText(materia.getNombre());
        this.materia = materia;
    }

    @Override
    public void onClick(View view) {

    }

}
