package ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Persona;

public class OfertaFragment extends Fragment {

    private TextView numeroCurso;
    private TextView docente;
    private TableLayout horarios;
    private TextView vacantes;
    private LinearLayout modalidades;
    private LinearLayout ayudantes;

    private boolean condicional = false;

    private Curso curso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.oferta, container, false);

        init(rootView);

        return rootView;
    }

    private void init(View rootView) {
        numeroCurso = rootView.findViewById(R.id.ins_numero_curso);
        vacantes = rootView.findViewById(R.id.vacantes);
        docente = rootView.findViewById(R.id.ins_docente_curso);
        horarios =  rootView.findViewById(R.id.ins_tabla_horarios);
        modalidades = rootView.findViewById(R.id.modalidades);
        ayudantes = rootView.findViewById(R.id.ayudantes);

        curso = ((MainActivity) getActivity()).getCursoSeleccionado();

        bindCurso();

    }

    private void bindCurso() {
        numeroCurso.setText("Curso " + curso.getComision());
        docente.setText(curso.getDocente());
        vacantes.setText("Vacantes disponibles: " + curso.getVacantes());

        if (curso.getAyudantes().isEmpty()) {
            ayudantes.setVisibility(View.GONE);
        } else {
            for (Persona ayudante : curso.getAyudantes()) {
                TextView textViewAyudante = new TextView(getContext());
                textViewAyudante.setText(ayudante.toString());
                ayudantes.addView(textViewAyudante);
            }
        }

        TableRow header = new TableRow(getContext());
        TextView textViewDias = new TextView(getContext());
        textViewDias.setText("Días");
        textViewDias.setTextSize(14);
        textViewDias.setTextColor(getActivity().getColor(R.color.white));
        textViewDias.setBackgroundResource(R.color.colorPrimary);
        textViewDias.setPadding(8,8,8,8);

        TextView textViewHorarios = new TextView(getContext());
        textViewHorarios.setText("Horarios");
        textViewHorarios.setTextSize(14);
        textViewHorarios.setTextColor(getActivity().getColor(R.color.white));
        textViewHorarios.setBackgroundResource(R.color.colorPrimary);
        textViewHorarios.setPadding(8,8,8,8);

        TextView textViewHeaderSede = new TextView(getContext());
        textViewHeaderSede.setText("Sede");
        textViewHeaderSede.setTextSize(14);
        textViewHeaderSede.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        textViewHeaderSede.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderSede.setPadding(8,8,8,8);

        TextView textViewHeaderAula = new TextView(getContext());
        textViewHeaderAula.setText("Aula");
        textViewHeaderAula.setTextSize(14);
        textViewHeaderAula.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        textViewHeaderAula.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderAula.setPadding(8,8,8,8);

        header.addView(textViewDias);
        header.addView(textViewHorarios);
        header.addView(textViewHeaderSede);
        header.addView(textViewHeaderAula);

        horarios.addView(header);

        for (Horario horario : curso.getCursada()) {
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

            TextView textViewSede = new TextView(getContext());
            textViewSede.setText(horario.getSede());
            textViewSede.setBackgroundResource(R.drawable.cell_shape);
            textViewSede.setPadding(8,8,8,8);

            TextView textViewAula = new TextView(getContext());
            textViewAula.setText(horario.getAula());
            textViewAula.setBackgroundResource(R.drawable.cell_shape);
            textViewAula.setPadding(8,8,8,8);

            row.addView(textViewDia);
            row.addView(textViewHorario);
            row.addView(textViewSede);
            row.addView(textViewAula);

            horarios.addView(row);

            TextView modalidad = new TextView(getContext());
            String s_modalidad = horario.getDia() + ": " + horario.getTipo();
            modalidad.setText(s_modalidad);
            modalidad.setPadding(8,8,8,8);
            modalidades.addView(modalidad);
        }
    }
}
