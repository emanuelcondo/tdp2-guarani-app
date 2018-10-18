package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
        materia = ((MainActivity) getActivity()).getMateriaSeleccionada();
        if (materia == null) {
            Log.d("InscripcionCursosFragment", "No hay materia seleccionada");
        } else {
            cursos = materia.getCursos();
            ((MainActivity) getActivity()).setToolbarName(materia.getCodigo() + " " + materia.getNombre());
        }


    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        InscripcionCursosAdapter adapter = new InscripcionCursosAdapter(getActivity(), cursos);
        this.setConfiguredAdapter(adapter);
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}

