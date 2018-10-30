package ar.edu.uba.fi.tdp2.guaraniapp.prioridad;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestHelper;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseListener;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.desinscripcion.DesinscripcionExamenesListener;
import ar.edu.uba.fi.tdp2.guaraniapp.login.AlumnoListener;
import ar.edu.uba.fi.tdp2.guaraniapp.login.Token;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion.DesinscripcionCursosListener;

public class MiPrioridadFragment extends Fragment {

    private static final String TAG = "MiPrioridad";

    private boolean existePrioridad;

    private TextView textViewTitulo;
    private TextView textViewPrioridad;
    private TextView textViewFechaInscripcion;
    private LinearLayout linearLayoutError;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setToolbarName(getString(R.string.mi_prioridad_toolbar));

        return inflater.inflate(R.layout.fragment_mi_prioridad, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        textViewTitulo = view.findViewById(R.id.prioridad_titulo);
        textViewPrioridad = view.findViewById(R.id.prioridad_numero);
        textViewFechaInscripcion = view.findViewById(R.id.prioridad_fecha_inscripcion);
        linearLayoutError = view.findViewById(R.id.prioridad_error);

        existePrioridad = false;

        if (existePrioridad) {
            //TODO: Borrar mock
            textViewPrioridad.setText("127");
            textViewFechaInscripcion.setText(getString(R.string.prioridad_fecha) + " 20/08/2019 13:30");
        } else {
            textViewTitulo.setVisibility(View.GONE);
            textViewPrioridad.setVisibility(View.GONE);
            textViewFechaInscripcion.setVisibility(View.GONE);
            linearLayoutError.setVisibility(View.VISIBLE);
        }

    }
}

