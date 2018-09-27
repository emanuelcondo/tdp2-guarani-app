package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Carrera;

public class InscripcionCarrerasFragment  extends RecyclerFragment {

    private List<Carrera> carreras;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);


        ((MainActivity) getActivity()).setToolbarName(getString(R.string.seleccion_carrera));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setToolbarName(getString(R.string.seleccion_carrera));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        carreras = ((MainActivity) getActivity()).getUsuario().getCarreras();
        InscripcionCarrerasAdapter inscripcionCarrerasAdapter = new InscripcionCarrerasAdapter(getActivity(), carreras);
        this.setConfiguredAdapter(inscripcionCarrerasAdapter);
    }

}

