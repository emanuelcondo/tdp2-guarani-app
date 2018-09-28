package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;

public class InscripcionCursosAdapter extends RecyclerView.Adapter<InscripcionCursoViewHolder> {

    private MainActivity activity;

    private List<Curso> cursos;

    public InscripcionCursosAdapter(Activity activity, List<Curso> cursos) {
        this.cursos = cursos;
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public InscripcionCursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InscripcionCursoViewHolder(LayoutInflater.from(activity).inflate(R.layout.curso_item_layout, parent, false), activity);
    }

    @Override
    public void onBindViewHolder(@NonNull InscripcionCursoViewHolder holder, int position) {
        Curso curso = this.cursos.get(position);
        holder.bindTo(curso);
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }
}

