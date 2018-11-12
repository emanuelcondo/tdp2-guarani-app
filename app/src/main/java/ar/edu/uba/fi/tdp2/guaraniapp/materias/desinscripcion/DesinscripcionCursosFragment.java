package ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion;

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
        ((MainActivity) getActivity()).setToolbarName(getString(R.string.mis_inscripciones));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        DesinscripcionCursosAdapter adapter = new DesinscripcionCursosAdapter(getActivity());
        this.setConfiguredAdapter(adapter);
    }

    @Override
    public void onSuccess() {
        getAdapter().notifyDataSetChanged();

        if (getAdapter().getItemCount() == 0) {
            addErrorMessage();
        }
    }

    private void addErrorMessage() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View error = inflater.inflate(R.layout.error_message, null);
        TextView errorMessage = error.findViewById(R.id.error_message_text);
        errorMessage.setText(R.string.error_no_inscripciones);
        CoordinatorLayout view = getView().getRootView().findViewById(R.id.coordinator);
        view.addView(error);
    }

    @Override
    public void onError() {

        //Do nothing?
    }

}

