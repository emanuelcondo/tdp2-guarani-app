package ar.edu.uba.fi.tdp2.guaraniapp.historia;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;


public class HistoriaAcademicaFragment extends RecyclerFragment {

    private ArrayList<ResultadoExamen> resultados = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //TODO: Borrar esto cuando este implementado
        mockearHistorial();
    }

    private void mockearHistorial() {
        Materia materia1 = new Materia("75.01", "Algoritmos y Programación I", "Computacion");
        Materia materia2 = new Materia("71.26", "Modelos y Optimización II", "Gestion");
        Materia materia3 = new Materia("75.15", "Base de Datos", "Computacion");
        Materia materia4 = new Materia("75.46", "Administración y Control de Proyectos Informáticos II", "Computacion");
        Materia materia5 = new Materia("71.13", "Información en las Organizaciones", "Gestion");
        Materia materia6 = new Materia("75.47", "Taller de Desarrollo de Proyectos II", "Computacion");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 6, 15);
        Date date1 = calendar.getTime();
        calendar.set(2017, 12, 20);
        Date date2 = calendar.getTime();
        calendar.set(2018, 2, 14);
        Date date3 = calendar.getTime();

        ResultadoExamen resultado1 = new ResultadoExamen(materia1, date1, 8);
        ResultadoExamen resultado2 = new ResultadoExamen(materia2, date2, 5);
        ResultadoExamen resultado3 = new ResultadoExamen(materia3, date3, 2);
        resultados.add(resultado1);
        resultados.add(resultado2);
        resultados.add(resultado3);
        resultados.add(new ResultadoExamen(materia4, date1, 7));
        resultados.add(new ResultadoExamen(materia5, date2, 10));
        resultados.add(new ResultadoExamen(materia6, date3, 9));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        HistoriaAcademicaAdapter historiaAcademicaAdapter = new HistoriaAcademicaAdapter(getActivity(), resultados);
        this.setConfiguredAdapter(historiaAcademicaAdapter);
    }

}

