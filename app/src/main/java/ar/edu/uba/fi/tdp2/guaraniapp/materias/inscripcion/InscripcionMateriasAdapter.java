package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;

public class InscripcionMateriasAdapter extends RecyclerView.Adapter<InscripcionMateriaViewHolder> {

    private MainActivity activity;

    private List<Materia> materias;

    public InscripcionMateriasAdapter(Activity activity, List<Materia> materias) {
        this.materias = materias;
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public InscripcionMateriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InscripcionMateriaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.materia_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InscripcionMateriaViewHolder holder, int position) {
        Materia materia = this.materias.get(position);
        holder.bindTo(materia, activity.getUsuario());
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }
}

