package ar.edu.uba.fi.tdp2.guaraniapp.encuestas;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.model.EncuestaCurso;

public class EncuestasCursosAdapter extends RecyclerView.Adapter<EncuestasCursoViewHolder> {

    private MainActivity activity;

    public EncuestasCursosAdapter(Activity activity) {
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public EncuestasCursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EncuestasCursoViewHolder(LayoutInflater.from(activity).inflate(R.layout.encuesta_curso_item_layout, parent, false), activity);
    }

    @Override
    public void onBindViewHolder(@NonNull EncuestasCursoViewHolder holder, int position) {
        EncuestaCurso encuestaCurso = getEncuestaCursos().get(position);
        holder.bindTo(encuestaCurso);
    }

    @Override
    public int getItemCount() {
        return getEncuestaCursos().size();
    }


    private List<EncuestaCurso> getEncuestaCursos() {
        return activity.getEncuestaCursos();
    }
}

