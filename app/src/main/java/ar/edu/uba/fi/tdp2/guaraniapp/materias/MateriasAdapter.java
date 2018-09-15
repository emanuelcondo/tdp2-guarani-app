package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;

public class MateriasAdapter extends RecyclerView.Adapter<MateriaViewHolder> {

    private MainActivity activity;

    public MateriasAdapter(/*lista,*/ Activity activity)
    {
        /*this.lista = lista;*/
        this.activity = (MainActivity)activity;
    }

    @Override
    public MateriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_card_layout, parent, false);
        MateriaViewHolder vh = new MateriaViewHolder(v, activity);
        return vh;
    }

    @Override
    public void onBindViewHolder(MateriaViewHolder holder, int position) {

        /*Materia materia = //this.lista.get(position);
        String nombre = materia.getNombre();


        TextView txtNombre = holder.textViewNombre;
        txtNombre.setText(nombre);

        TextView txtDescripcion = holder.textViewDesc;*/
        /*if (materia.getDescripcion().trim().length() > 1) {
            txtDescripcion.setText(materia.getDescripcion());
        } else {
            String completada = "Completa: " + (materia.estaCompleta() ? "SI" : "NO");
            txtDescripcion.setText(completada);
        }*/

        TextView txtFecha= holder.textViewFecha;
        //txtFecha.setText(materia.getFechaInicioMesDia());

        // me guardo la posicion de los datos que cargue en este VH
        holder.position = position;

        //Guardo la actividad en el holder
        //holder.asociarActividad(materia);
        holder.adapter = this;
    }

    @Override
    public int getItemCount() {
        /*return lista.size();*/
        return 0;
    }
}

