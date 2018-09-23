package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;


public class InscripcionMateriasFragment extends RecyclerFragment {

    private ArrayList<Materia> materias;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        materias = ((MainActivity) getActivity()).getMaterias();

        ((MainActivity) getActivity()).setToolbarName("Inscripción a Cursos");
     }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        InscripcionMateriasAdapter inscripcionMateriasAdapter = new InscripcionMateriasAdapter(getActivity(), materias);
        this.setConfiguredAdapter(inscripcionMateriasAdapter);
    }

}

