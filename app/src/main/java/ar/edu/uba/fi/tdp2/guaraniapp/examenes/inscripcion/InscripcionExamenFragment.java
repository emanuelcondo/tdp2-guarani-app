package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Materia;

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
            titulo = materia.getNombre();
        } else {
            titulo = getString(R.string.seleccion_fechas_examenes);
        }
        ((MainActivity) getActivity()).setToolbarName(titulo);

        if (getAdapter().getItemCount() == 0) {
            addErrorMessage();
        }
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        InscripcionExamenAdapter adapter = new InscripcionExamenAdapter(getActivity(), this);
        this.setConfiguredAdapter(adapter);
    }

    private void addErrorMessage() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View error = inflater.inflate(R.layout.error_message, null);
        TextView errorMessage = error.findViewById(R.id.error_message_text);
        errorMessage.setText(R.string.error_no_examenes);
        CoordinatorLayout view = getView().getRootView().findViewById(R.id.coordinator);
        view.addView(error);
    }

}

