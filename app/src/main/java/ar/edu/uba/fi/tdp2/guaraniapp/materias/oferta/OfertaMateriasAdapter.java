package ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;

public class OfertaMateriasAdapter extends RecyclerView.Adapter<OfertaMateriaViewHolder> {

    private MainActivity activity;

    private ArrayList<Materia> materias;

    public OfertaMateriasAdapter(Activity activity, ArrayList<Materia> materias) {
        this.materias = materias;
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public OfertaMateriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OfertaMateriaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.materia_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OfertaMateriaViewHolder holder, int position) {
        Materia materia = this.materias.get(position);
        holder.bindTo(materia, activity.getUsuario());
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }
}

