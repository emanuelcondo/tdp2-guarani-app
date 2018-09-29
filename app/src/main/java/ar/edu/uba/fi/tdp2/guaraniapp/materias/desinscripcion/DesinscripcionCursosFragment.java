package ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Inscripcion;

public class DesinscripcionCursosFragment extends RecyclerFragment {

    private List<Inscripcion> inscripciones = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

        loadInscripciones();

        ((MainActivity) getActivity()).setToolbarName(getString(R.string.desinscripcion));
    }

    private void loadInscripciones() {
        Context context = getActivity();
        DesinscripcionCursosListener desinscripcionCursosListener = new DesinscripcionCursosListener(context, this);
        RequestSender requestSender = new RequestSender(context);

        String url = context.getString(R.string.urlAppServer) + "inscripciones/cursos/";

        requestSender.doGet_expectJSONObject(desinscripcionCursosListener, url);
    }

    @Override
    public void onResume() {
        super.onResume();
        //loadInscripciones();

        ((MainActivity) getActivity()).setToolbarName(getString(R.string.desinscripcion));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        DesinscripcionCursosAdapter desinscripcionCursosAdapter = new DesinscripcionCursosAdapter(getActivity(), this);
        this.setConfiguredAdapter(desinscripcionCursosAdapter);
    }


    public void onSuccess(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
        this.getAdapter().notifyDataSetChanged();
    }

    public List<Inscripcion> getInscripciones() {
        return this.inscripciones;
    }
}

