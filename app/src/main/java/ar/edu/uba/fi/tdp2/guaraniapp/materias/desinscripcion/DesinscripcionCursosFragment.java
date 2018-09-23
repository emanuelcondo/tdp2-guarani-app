package ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion.DesinscripcionCursosAdapter;


public class DesinscripcionCursosFragment extends RecyclerFragment {

    private ArrayList<Curso> cursos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        cursos = ((MainActivity) getActivity()).getUsuario().getInscripciones();

        ((MainActivity) getActivity()).setToolbarName("Desinscripción a Cursos");
    }

    @Override
    public void onResume() {
        super.onResume();
        cursos = ((MainActivity) getActivity()).getUsuario().getInscripciones();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        DesinscripcionCursosAdapter desinscripcionCursosAdapter = new DesinscripcionCursosAdapter(getActivity(), cursos);
        this.setConfiguredAdapter(desinscripcionCursosAdapter);
    }

}

