package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.FechaExamen;

public class InscripcionExamenAdapter extends RecyclerView.Adapter<InscripcionExamenViewHolder> {

    private MainActivity activity;

    public InscripcionExamenAdapter(Activity activity) {
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public InscripcionExamenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InscripcionExamenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fecha_examen_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InscripcionExamenViewHolder holder, int position) {
        List<FechaExamen> fechasExamen = activity.getFechasExamen();
        FechaExamen fechaExamen = fechasExamen.get(position);
        holder.bindTo(fechaExamen);
    }

    @Override
    public int getItemCount() {
        return activity.getFechasExamen().size();
    }
}

