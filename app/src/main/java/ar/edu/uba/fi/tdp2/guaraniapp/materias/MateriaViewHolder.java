package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;

public class MateriaViewHolder  extends RecyclerView.ViewHolder
        implements View.OnClickListener  {
    public TextView textViewNombre;
    public TextView textViewDesc;
    public TextView textViewFecha;
    public ImageView imageViewCompletada;
    public int position;

    public Materia materia;
    public MateriasAdapter adapter;

    private MainActivity mainActivity;

    public MateriaViewHolder(View itemView, MainActivity mainActivity) {
        super(itemView);
        this.mainActivity = mainActivity;

        // hago los find y cargo los atributos
        /*
        textViewNombre = itemView.findViewById(R.id.txtNombre);
        textViewDesc = itemView.findViewById(R.id.txtCompletada);

        textViewFecha = itemView.findViewById(R.id.txtFecha);

        imageViewCompletada = itemView.findViewById(R.id.imageViewCompletarActividad);

        */


        imageViewCompletada.setOnClickListener(this);
        textViewNombre.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        /*if (view == this.imageViewCompletada) {
            this.actividad.alternarEstadoCompleta();
            this.adapter.notifyDataSetChanged();
            this.mostrarImagenCompletada();
        }
        else if (view == this.textViewNombre) {
            this.mainActivity.setActividad_detalle(this.actividad);
            FragmentLoader.load(mainActivity, new DetalleActividadAgregarParticipantes(), FragmentLoader.DetalleActividadAgregarParticipantes);
        }*/

    }

    /*public void asociarActividad(Actividad actividad) {
        this.actividad = actividad;
        this.mostrarImagenCompletada();
    }

    private void mostrarImagenCompletada() {

        int image = actividad.estaCompleta() ? R.drawable.tick : R.drawable.reloj;

        imageViewCompletada.setImageResource(image);
    }*/
}
