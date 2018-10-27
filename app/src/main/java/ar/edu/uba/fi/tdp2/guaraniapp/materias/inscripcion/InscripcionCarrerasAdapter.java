package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Carrera;

public class InscripcionCarrerasAdapter extends RecyclerView.Adapter<InscripcionCarreraViewHolder> {

    private MainActivity activity;

    public InscripcionCarrerasAdapter(Activity activity) {
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public InscripcionCarreraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InscripcionCarreraViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.carrera_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InscripcionCarreraViewHolder holder, int position) {
        List<Carrera> carreras = activity.getAlumno().getCarreras();
        Carrera carrera = carreras.get(position);
        holder.bindTo(carrera);
    }

    @Override
    public int getItemCount() {
        return activity.getAlumno().getCarreras().size();
    }
}

