package ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;

public class DesinscripcionCursosAdapter extends RecyclerView.Adapter<DesinscripcionCursoViewHolder> {

    private MainActivity activity;

    private ArrayList<Curso> cursos;

    public DesinscripcionCursosAdapter(Activity activity, ArrayList<Curso> cursos) {
        this.cursos = cursos;
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public DesinscripcionCursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DesinscripcionCursoViewHolder(LayoutInflater.from(activity).inflate(R.layout.curso_item_layout, parent, false), activity);
    }

    @Override
    public void onBindViewHolder(@NonNull DesinscripcionCursoViewHolder holder, int position) {
        Curso curso = this.cursos.get(position);
        holder.bindTo(curso);
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }
}

