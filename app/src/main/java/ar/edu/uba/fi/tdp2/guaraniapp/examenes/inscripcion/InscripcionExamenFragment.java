package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;

public class InscripcionExamenFragment extends RecyclerFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

        //TODO: crear string

        //((MainActivity) getActivity()).setToolbarName(getString(R.string.seleccion_carrera));
        ((MainActivity) getActivity()).setToolbarName("Fechas Examen");
    }

    @Override
    public void onResume() {
        super.onResume();
        //((MainActivity) getActivity()).setToolbarName(getString(R.string.seleccion_carrera));
        ((MainActivity) getActivity()).setToolbarName("Fechas Examen");
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        InscripcionExamenAdapter inscripcionExamenAdapter = new InscripcionExamenAdapter(getActivity());
        this.setConfiguredAdapter(inscripcionExamenAdapter);
    }

}

