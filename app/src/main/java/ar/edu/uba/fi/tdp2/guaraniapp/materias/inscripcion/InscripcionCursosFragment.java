package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;


public class InscripcionCursosFragment extends RecyclerFragment {

    private Materia materia;
    private List<Curso> cursos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cursos = materia.getCursos();
        cursos.removeAll(((MainActivity) getActivity()).getUsuario().getInscripciones());
        ((MainActivity) getActivity()).setToolbarName(materia.getCodigo() + " " + materia.getNombre());

    }

    @Override
    public void onResume() {
        super.onResume();
        cursos = materia.getCursos();
        cursos.removeAll(((MainActivity) getActivity()).getUsuario().getInscripciones());
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        InscripcionCursosAdapter inscripcionCursosAdapter = new InscripcionCursosAdapter(getActivity(), cursos);
        this.setConfiguredAdapter(inscripcionCursosAdapter);
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}

