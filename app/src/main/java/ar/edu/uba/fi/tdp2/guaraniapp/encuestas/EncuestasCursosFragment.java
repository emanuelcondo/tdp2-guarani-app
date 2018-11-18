package ar.edu.uba.fi.tdp2.guaraniapp.encuestas;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.EncuestaCurso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Persona;

public class EncuestasCursosFragment extends RecyclerFragment implements ResponseWatcher {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

    }

    private void loadEncuestas() {
        Context context = getActivity();
        EncuestasCursosListener encuestasCursosListener = new EncuestasCursosListener(context, this);
        RequestSender requestSender = new RequestSender(context);

        String url = context.getString(R.string.urlAppServer) + "encuestas/alumnos/mis-encuestas";

        requestSender.doGet_expectJSONObject(encuestasCursosListener, url);
    }

    @Override
    public void onResume() {
        //OnResume se llama siempre despues de OnStart y cada vez que se vuelve al fragment
        super.onResume();
        getAdapter().notifyDataSetChanged();
        ((MainActivity) getActivity()).setToolbarName(getString(R.string.encuestas_cursos));

        //TODO: Borrar mock
        final Materia materia = new Materia();
        materia.setCodigo("75.01");
        materia.setNombre("Algoritmos y Programación I");
        final Curso curso = new Curso();
        curso.setDocenteACargo(new Persona("Carlos", "Perez"));
        ((MainActivity) getActivity()).setEncuestasCursos(new ArrayList<EncuestaCurso>() {{
            add(new EncuestaCurso(curso, 0, 2018, 2, materia));
        }});
        onSuccess();

        //loadEncuestas();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        EncuestasCursosAdapter adapter = new EncuestasCursosAdapter(getActivity());
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
        errorMessage.setText(R.string.error_no_encuestas);
        CoordinatorLayout view = getView().getRootView().findViewById(R.id.coordinator);
        view.addView(error);
    }

    @Override
    public void onError() {
    }

}

