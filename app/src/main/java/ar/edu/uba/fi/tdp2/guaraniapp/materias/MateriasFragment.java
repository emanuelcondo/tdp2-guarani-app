package ar.edu.uba.fi.tdp2.guaraniapp.materias;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;


public class MateriasFragment extends RecyclerFragment {

    private ArrayList<Materia> materias = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //TODO: Borrar esto cuando este implementado
        mockearMaterias();
    }

    private void mockearMaterias() {
        Materia materia1 = new Materia("75.01", "Algoritmos y Programación I");
        materia1.agregarCurso(new Curso("Gustavo Campagnuolo", "Lunes 19-22, Martes 19-22"));
        materia1.agregarCurso(new Curso("Reinaldo Merlo", "Miércoles 15-22"));
        Materia materia2 = new Materia("75.26", "Arquitectura de Software");
        materia2.agregarCurso(new Curso("José Chatruc", "Viernes 15-23"));
        materia2.agregarCurso(new Curso("Adrián Bastía", "Lunes 19-22, Jueves 19-22"));
        materias.add(materia1);
        materias.add(materia2);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        MateriasAdapter materiasAdapter = new MateriasAdapter(getActivity(), materias);
        this.setConfiguredAdapter(materiasAdapter);
    }

}

