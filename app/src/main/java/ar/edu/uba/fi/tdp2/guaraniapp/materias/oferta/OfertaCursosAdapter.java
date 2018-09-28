package ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;

public class OfertaCursosAdapter extends RecyclerView.Adapter<OfertaCursoViewHolder> {

    private MainActivity activity;

    private List<Curso> cursos;

    public OfertaCursosAdapter(Activity activity, List<Curso> cursos) {
        this.cursos = cursos;
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public OfertaCursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OfertaCursoViewHolder(LayoutInflater.from(activity).inflate(R.layout.curso_item_layout, parent, false), activity);
    }

    @Override
    public void onBindViewHolder(@NonNull OfertaCursoViewHolder holder, int position) {
        Curso curso = this.cursos.get(position);
        holder.bindTo(curso);
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }
}

