package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Carrera;

public class InscripcionExamenCarrerasAdapter extends RecyclerView.Adapter<InscripcionExamenCarreraViewHolder> {

    private MainActivity activity;

    public InscripcionExamenCarrerasAdapter(Activity activity) {
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public InscripcionExamenCarreraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InscripcionExamenCarreraViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.carrera_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InscripcionExamenCarreraViewHolder holder, int position) {
        List<Carrera> carreras = activity.getAlumno().getCarreras();
        Carrera carrera = carreras.get(position);
        holder.bindTo(carrera);
    }

    @Override
    public int getItemCount() {
        return activity.getAlumno().getCarreras().size();
    }
}

