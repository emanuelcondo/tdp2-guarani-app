package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;

public class InscripcionExamenFragment extends RecyclerFragment {

    private Materia materia;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(false);
        materia = ((MainActivity) getActivity()).getMateriaSeleccionada();
        String titulo = "";
        if (materia != null) {
            titulo = materia.getCodigo() + " " + materia.getNombre();
        } else {
            titulo = getString(R.string.seleccion_fechas_examenes);
        }
        ((MainActivity) getActivity()).setToolbarName(titulo);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        InscripcionExamenAdapter adapter = new InscripcionExamenAdapter(getActivity());
        this.setConfiguredAdapter(adapter);
    }

}

