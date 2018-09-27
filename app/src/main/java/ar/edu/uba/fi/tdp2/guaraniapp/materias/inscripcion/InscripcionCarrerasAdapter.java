package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Carrera;

public class InscripcionCarrerasAdapter extends RecyclerView.Adapter<InscripcionCarreraViewHolder> {

    private MainActivity activity;

    private List<Carrera> carreras;

    public InscripcionCarrerasAdapter(Activity activity, List<Carrera> carreras) {
        this.carreras = carreras;
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public InscripcionCarreraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InscripcionCarreraViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.carrera_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InscripcionCarreraViewHolder holder, int position) {
        Carrera carrera = this.carreras.get(position);
        holder.bindTo(carrera);
    }

    @Override
    public int getItemCount() {
        return carreras.size();
    }
}

