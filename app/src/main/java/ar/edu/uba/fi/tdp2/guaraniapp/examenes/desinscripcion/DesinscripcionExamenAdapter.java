package ar.edu.uba.fi.tdp2.guaraniapp.examenes.desinscripcion;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.InscripcionExamen;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Inscripcion;

public class DesinscripcionExamenAdapter extends RecyclerView.Adapter<DesinscripcionExamenViewHolder> {

    private MainActivity activity;
    private DesinscripcionExamenFragment desinscripcionExamenFragment;

    public DesinscripcionExamenAdapter(Activity activity, DesinscripcionExamenFragment desinscripcionExamenFragment) {
        this.activity = (MainActivity)activity;
        this.desinscripcionExamenFragment = desinscripcionExamenFragment;
    }

    @NonNull
    @Override
    public DesinscripcionExamenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DesinscripcionExamenViewHolder(LayoutInflater.from(activity).inflate(R.layout.inscripcion_examen_item_layout, parent, false), activity, desinscripcionExamenFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull DesinscripcionExamenViewHolder holder, int position) {
        InscripcionExamen inscripcion = getInscripcionesExamenes().get(position);
        holder.bindTo(inscripcion);
    }

    @Override
    public int getItemCount() {
        return getInscripcionesExamenes().size();
    }


    private List<InscripcionExamen> getInscripcionesExamenes() {
        return activity.getAlumno().getInscripcionesExamenes();
    }
}

