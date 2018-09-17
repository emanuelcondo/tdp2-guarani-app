package ar.edu.uba.fi.tdp2.guaraniapp.historia;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.MateriaViewHolder;

public class HistoriaAcademicaAdapter extends RecyclerView.Adapter<HistoriaAcademicaViewHolder> {

    private MainActivity activity;

    private ArrayList<ResultadoExamen> resultados;

    public HistoriaAcademicaAdapter(Activity activity, ArrayList<ResultadoExamen> resultados) {
        this.resultados = resultados;
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public HistoriaAcademicaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoriaAcademicaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.resultado_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriaAcademicaViewHolder holder, int position) {
        ResultadoExamen resultadoExamen = this.resultados.get(position);
        holder.bindTo(resultadoExamen);
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }
}

