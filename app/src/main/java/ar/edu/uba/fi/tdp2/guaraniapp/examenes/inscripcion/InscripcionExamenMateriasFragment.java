package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionMateriasAdapter;


public class InscripcionExamenMateriasFragment extends RecyclerFragment {

    private List<Materia> materias;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        materias = ((MainActivity) getActivity()).getMaterias();
     }

     @Override
     public void onResume() {
        super.onResume();
         ((MainActivity) getActivity()).setToolbarName(getString(R.string.seleccion_fechas_examenes));
     }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        InscripcionExamenMateriasAdapter adapter = new InscripcionExamenMateriasAdapter(getActivity(), materias);
        this.setConfiguredAdapter(adapter);
    }

}

