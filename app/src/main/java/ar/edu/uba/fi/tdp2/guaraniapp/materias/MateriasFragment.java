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
        if (materias.size()>0 ) {
            return;
        }
        Materia materia1 = new Materia("75.01", "Algoritmos y Programación I", "Computacion");
        ArrayList<Horario> horarios1 = new ArrayList<Horario>() {{
            add(new Horario("Lunes",19,22));
            add(new Horario("Martes", 19, 22));
        }};
        materia1.agregarCurso(new Curso(12,"Gustavo Campagnuolo", horarios1));
        materia1.getCursos().get(0).setObservaciones("Solo para recursantes");
        ArrayList<Horario> horarios2 = new ArrayList<Horario>() {{
            add(new Horario("Miércoles",15,22));
        }};
        materia1.agregarCurso(new Curso(2,"Reinaldo Merlo", horarios2));
        Materia materia2 = new Materia("71.26", "Modelos y Optimización II", "Gestion");
        ArrayList<Horario> horarios3 = new ArrayList<Horario>() {{
            add(new Horario("Viernes",15,23));
        }};
        materia2.agregarCurso(new Curso(1, "José Chatruc", horarios3));
        ArrayList<Horario> horarios4 = new ArrayList<Horario>() {{
            add(new Horario("Lunes",19,22));
            add(new Horario("Martes", 19, 20));
            add(new Horario("Jueves", 20, 22));
        }};
        materia2.agregarCurso(new Curso(2, "Adrián Bastía", horarios4));
        materias.clear();
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

