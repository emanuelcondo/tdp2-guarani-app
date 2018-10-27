package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Examen;

public class InscripcionExamenAdapter extends RecyclerView.Adapter<InscripcionExamenViewHolder> {

    private MainActivity activity;
    private InscripcionExamenFragment inscripcionExamenFragment;

    public InscripcionExamenAdapter(Activity activity, InscripcionExamenFragment inscripcionExamenFragment) {
        this.activity = (MainActivity)activity;
        this.inscripcionExamenFragment = inscripcionExamenFragment;
    }

    @NonNull
    @Override
    public InscripcionExamenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InscripcionExamenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fecha_examen_item_layout, parent, false), inscripcionExamenFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull InscripcionExamenViewHolder holder, int position) {
        List<Examen> fechasExamen = activity.getFechasExamen();
        Examen examen = fechasExamen.get(position);
        holder.bindTo(examen);
    }

    @Override
    public int getItemCount() {
        return activity.getFechasExamen().size();
    }
}

