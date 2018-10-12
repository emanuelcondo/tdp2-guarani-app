package ar.edu.uba.fi.tdp2.guaraniapp.examenes;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Carrera;

public class FechasExamenAdapter extends RecyclerView.Adapter<FechaExamenViewHolder> {

    private MainActivity activity;

    public FechasExamenAdapter(Activity activity) {
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public FechaExamenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FechaExamenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fecha_examen_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FechaExamenViewHolder holder, int position) {
        List<FechaExamen> fechasExamen = activity.getFechasExamen();
        FechaExamen fechaExamen = fechasExamen.get(position);
        holder.bindTo(fechaExamen);
    }

    @Override
    public int getItemCount() {
        return activity.getFechasExamen().size();
    }
}

