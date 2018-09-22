package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;

public class CursosAdapter extends RecyclerView.Adapter<CursoViewHolder> {

    private MainActivity activity;

    private ArrayList<Curso> cursos;

    public CursosAdapter(Activity activity, ArrayList<Curso> cursos) {
        this.cursos = cursos;
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CursoViewHolder(LayoutInflater.from(activity).inflate(R.layout.curso_item_layout, parent, false), activity);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        Curso curso = this.cursos.get(position);
        holder.bindTo(curso);
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }
}

