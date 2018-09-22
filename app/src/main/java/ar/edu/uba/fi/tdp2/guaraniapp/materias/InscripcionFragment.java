package ar.edu.uba.fi.tdp2.guaraniapp.materias;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;

public class InscripcionFragment extends Fragment {

    private TextView numeroCurso;
    private TextView docente;
    private TableLayout horarios;
    private TextView observaciones;
    private Button btnInscribir;

    private Curso curso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.inscripcion, container, false);

        init(rootView);

        return rootView;
    }

    private void init(View rootView) {
        numeroCurso = rootView.findViewById(R.id.ins_numero_curso);
        observaciones = rootView.findViewById(R.id.ins_observaciones);
        docente = rootView.findViewById(R.id.ins_docente_curso);
        horarios =  rootView.findViewById(R.id.ins_tabla_horarios);
        btnInscribir = rootView.findViewById(R.id.ins_btn_inscribir);

        curso = ((MainActivity) getActivity()).getCursoSeleccionado();

        bindCurso();
        //FragmentLoader.setBackOptionEnabled(getActivity(), true);

    }

    private void bindCurso() {
        numeroCurso.setText("Curso " + curso.getNumeroCurso());
        docente.setText(curso.getDocente());
        observaciones.setText(curso.getObservaciones());

        TableRow header = new TableRow(getContext());
        TextView textViewDias = new TextView(getContext());
        textViewDias.setText("Días");
        textViewDias.setTextSize(14);
        textViewDias.setTextColor(getActivity().getColor(R.color.colorPrimaryDark));
        textViewDias.setBackgroundResource(R.drawable.cell_shape);
        textViewDias.setPadding(8,8,8,8);
        TextView textViewHorarios = new TextView(getContext());
        textViewHorarios.setText("Horarios");
        textViewHorarios.setTextSize(14);
        textViewHorarios.setTextColor(getActivity().getColor(R.color.colorPrimaryDark));
        textViewHorarios.setBackgroundResource(R.drawable.cell_shape);
        textViewHorarios.setPadding(8,8,8,8);
        header.addView(textViewDias);
        header.addView(textViewHorarios);

        horarios.addView(header);

        for (Horario horario : curso.getHorarios()) {
            TableRow row = new TableRow(getContext());
            TextView textViewDia = new TextView(getContext());
            textViewDia.setText(horario.getDia());
            textViewDia.setBackgroundResource(R.drawable.cell_shape);
            textViewDia.setPadding(8,8,8,8);
            TextView textViewHorario = new TextView(getContext());
            String s_horario = horario.getHoraInicio() + "hs - " + horario.getHoraFin() + "hs";
            textViewHorario.setText(s_horario);
            textViewHorario.setBackgroundResource(R.drawable.cell_shape);
            textViewHorario.setPadding(8,8,8,8);
            row.addView(textViewDia);
            row.addView(textViewHorario);

            horarios.addView(row);
        }
    }
}
