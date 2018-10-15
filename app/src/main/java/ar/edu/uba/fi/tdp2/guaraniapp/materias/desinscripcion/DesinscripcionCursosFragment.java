package ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;

public class DesinscripcionCursosFragment extends RecyclerFragment implements ResponseWatcher {

    //private ProgressPopup progressPopup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

        //progressPopup = new ProgressPopup("Cargando inscripciones...", getContext());

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
        //OnResume se llama siempre despues de OnStart y cada vez que se vuelve al fragment
        super.onResume();
        //progressPopup.show();
        getAdapter().notifyDataSetChanged();
        loadInscripciones();
        ((MainActivity) getActivity()).setToolbarName(getString(R.string.desinscripcion));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        DesinscripcionCursosAdapter desinscripcionCursosAdapter = new DesinscripcionCursosAdapter(getActivity());
        this.setConfiguredAdapter(desinscripcionCursosAdapter);
    }

    @Override
    public void onSuccess() {

        getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onError() {

        //Do nothing?
    }


    /*
    public void onSuccess(List<Inscripcion> inscripciones) {
        //this.inscripciones = inscripciones;
        this.getAdapter().notifyDataSetChanged();
        progressPopup.dismiss();
    }*/

    /*public void onError() {
        progressPopup.dismiss();
    }*/

    /*public List<Inscripcion> getInscripciones() {
        return this.inscripciones;
    }*/
}

