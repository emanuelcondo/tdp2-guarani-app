package ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Inscripcion;

public class DesinscripcionCursosAdapter extends RecyclerView.Adapter<DesinscripcionCursoViewHolder> {

    private MainActivity activity;

    public DesinscripcionCursosAdapter(Activity activity) {
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public DesinscripcionCursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DesinscripcionCursoViewHolder(LayoutInflater.from(activity).inflate(R.layout.inscripcion_item_layout, parent, false), activity);
    }

    @Override
    public void onBindViewHolder(@NonNull DesinscripcionCursoViewHolder holder, int position) {
        Inscripcion inscripcion = getInscripciones().get(position);
        holder.bindTo(inscripcion);
    }

    @Override
    public int getItemCount() {
        return getInscripciones().size();
    }


    private List<Inscripcion> getInscripciones() {
        return activity.getAlumno().getInscripciones();
    }
}

