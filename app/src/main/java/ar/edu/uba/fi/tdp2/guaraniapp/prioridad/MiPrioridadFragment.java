package ar.edu.uba.fi.tdp2.guaraniapp.prioridad;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;

public class MiPrioridadFragment extends Fragment {

    private static final String TAG = "MiPrioridad";

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

        MainActivity activity = (MainActivity) getActivity();

        if (activity.existePrioridad()) {
            textViewPrioridad.setText(String.format(Locale.getDefault()
                    , "%d", activity.getAlumno().getPrioridad()));
            String fechaInscipcion = new SimpleDateFormat("dd/MM/yyyy HH:mm"
                    , Locale.getDefault())
                    .format(activity.getFechaInicioInscripcion());
            textViewFechaInscripcion.
                    setText(getString(R.string.prioridad_fecha) + " "
                            + fechaInscipcion);
        } else {
            textViewTitulo.setText("Tu prioridad aún no está cargada");
            textViewPrioridad.setText("?");
            String fechaPrioridades = new SimpleDateFormat("dd/MM/yyyy HH:mm"
                    , Locale.getDefault())
                    .format(activity.getFechaPublicacionPrioridades());
            textViewFechaInscripcion.setText("Las prioridades serán cargadas el día " + fechaPrioridades);
        }

    }
}

