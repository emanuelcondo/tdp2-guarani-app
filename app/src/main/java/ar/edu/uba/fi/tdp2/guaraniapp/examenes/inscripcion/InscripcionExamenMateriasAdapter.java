package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionMateriaViewHolder;

public class InscripcionExamenMateriasAdapter extends RecyclerView.Adapter<InscripcionExamenMateriaViewHolder> {


    private List<Materia> materias;

    public InscripcionExamenMateriasAdapter(Activity activity, List<Materia> materias) {
        this.materias = materias;
    }

    @NonNull
    @Override
    public InscripcionExamenMateriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InscripcionExamenMateriaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.materia_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InscripcionExamenMateriaViewHolder holder, int position) {
        Materia materia = this.materias.get(position);
        holder.bindTo(materia);
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }
}

