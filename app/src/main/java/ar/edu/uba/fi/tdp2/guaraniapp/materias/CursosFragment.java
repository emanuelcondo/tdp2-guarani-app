package ar.edu.uba.fi.tdp2.guaraniapp.materias;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;


public class CursosFragment extends RecyclerFragment {

    private ArrayList<Curso> cursos;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        CursosAdapter cursosAdapter = new CursosAdapter(getActivity(), cursos);
        this.setConfiguredAdapter(cursosAdapter);
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
}

