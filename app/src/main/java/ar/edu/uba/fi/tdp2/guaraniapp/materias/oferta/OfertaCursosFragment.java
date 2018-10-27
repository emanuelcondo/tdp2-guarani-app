package ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Materia;

public class OfertaCursosFragment extends RecyclerFragment {

    private Materia materia;
    private List<Curso> cursos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        materia = ((MainActivity) getActivity()).getMateriaSeleccionada();
        cursos = materia.getCursos();
        ((MainActivity) getActivity()).setToolbarName(materia.getCodigo() + " " + materia.getNombre());

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        OfertaCursosAdapter adapter = new OfertaCursosAdapter(getActivity(), cursos);
        this.setConfiguredAdapter(adapter);
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}

