package ar.edu.uba.fi.tdp2.guaraniapp.examenes.desinscripcion;

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

public class DesinscripcionExamenFragment extends RecyclerFragment implements ResponseWatcher {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    private void loadInscripcionesExamenes() {
        Context context = getActivity();
        DesinscripcionExamenesListener listener = new DesinscripcionExamenesListener(context, this);
        RequestSender requestSender = new RequestSender(context);


        String url = context.getString(R.string.urlAppServer) + "inscripciones/examenes";

        requestSender.doGet_expectJSONObject(listener, url);
    }

    @Override
    public void onResume() {
        //OnResume se llama siempre despues de OnStart y cada vez que se vuelve al fragment
        super.onResume();
        //progressPopup.show();
        getAdapter().notifyDataSetChanged();
        loadInscripcionesExamenes();
        ((MainActivity) getActivity()).setToolbarName(getString(R.string.desinscripcion_examen));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        DesinscripcionExamenAdapter adapter = new DesinscripcionExamenAdapter(getActivity());
        this.setConfiguredAdapter(adapter);
    }

    @Override
    public void onSuccess() {

        getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onError() {

        //Do nothing?
    }

}

