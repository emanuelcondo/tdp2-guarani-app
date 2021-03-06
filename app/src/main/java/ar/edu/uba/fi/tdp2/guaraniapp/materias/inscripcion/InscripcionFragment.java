package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONObject;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Persona;

public class InscripcionFragment extends Fragment  implements ResponseWatcher {

    private TextView numeroCurso;
    private TextView docente;
    private TableLayout horarios;
    private TextView vacantes;
    private Button btnInscribir;
    private LinearLayout modalidades;
    private LinearLayout ayudantes;
    private TextView mensajeCondicionales;

    private ProgressPopup progressPopup;

    private boolean condicional = false;

    private Curso curso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.inscripcion, container, false);

        init(rootView);

        progressPopup = new ProgressPopup("Inscribiendo...", rootView.getContext());

        return rootView;
    }

    private void init(View rootView) {
        numeroCurso = rootView.findViewById(R.id.ins_numero_curso);
        vacantes = rootView.findViewById(R.id.vacantes);
        docente = rootView.findViewById(R.id.ins_docente_curso);
        horarios =  rootView.findViewById(R.id.ins_tabla_horarios);
        modalidades = rootView.findViewById(R.id.modalidades);
        ayudantes = rootView.findViewById(R.id.ayudantes);
        btnInscribir = rootView.findViewById(R.id.ins_btn_inscribir);
        mensajeCondicionales = rootView.findViewById(R.id.mensaje_condicionales);

        btnInscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressPopup.show();
                inscribir();
            }
        });

        curso = ((MainActivity) getActivity()).getCursoSeleccionado();

        if (curso != null)
            bindCurso();

    }

    private void inscribir() {
        InscripcionListener listener = new InscripcionListener(getContext(), this);
        RequestSender requestSender = new RequestSender(getContext());

        String url = getContext().getString(R.string.urlAppServer) + "inscripciones/cursos/" + curso.get_id();

        requestSender.doPost(listener, url, new JSONObject());
    }


    private void bindCurso() {
        numeroCurso.setText(getString(R.string.curso_header, curso.getComision()));
        docente.setText(curso.getDocente());
        vacantes.setText(getString(R.string.vacantes_header, curso.getCupos(), curso.getVacantes()));

        if (curso.getVacantes() == 0) {
            btnInscribir.setText(R.string.inscribirse_como_condicional);
            condicional = true;
            if (hayVacantesEnOtrosCursos()) {
                //No se puede inscribir como condicional mientras haya vacantes en otros cursos
                btnInscribir.setEnabled(false);
                btnInscribir.setBackgroundResource(R.color.gray);
                mensajeCondicionales.setVisibility(View.VISIBLE);
            }
        }



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
        textViewDias.setText(R.string.dias_header);
        textViewDias.setTextSize(14);
        textViewDias.setTextColor(getActivity().getColor(R.color.white));
        textViewDias.setBackgroundResource(R.color.colorPrimary);
        textViewDias.setPadding(8,8,8,8);

        TextView textViewHorarios = new TextView(getContext());
        textViewHorarios.setText(R.string.horarios_header);
        textViewHorarios.setTextSize(14);
        textViewHorarios.setTextColor(getActivity().getColor(R.color.white));
        textViewHorarios.setBackgroundResource(R.color.colorPrimary);
        textViewHorarios.setPadding(8,8,8,8);

        TextView textViewHeaderSede = new TextView(getContext());
        textViewHeaderSede.setText(R.string.sede_header);
        textViewHeaderSede.setTextSize(14);
        textViewHeaderSede.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        textViewHeaderSede.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderSede.setPadding(8,8,8,8);

        TextView textViewHeaderAula = new TextView(getContext());
        textViewHeaderAula.setText(R.string.aula_header);
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

            textViewHorario.setText(getString(R.string.horario_row, horario.getHoraInicio(), horario.getHoraFin()));
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
            String s_modalidad = horario.getDia() + " " + horario.getHoraInicio() + " - " + horario.getHoraFin() + ": " + horario.getTipo();
            modalidad.setText(s_modalidad);
            modalidad.setPadding(8,8,8,8);
            modalidades.addView(modalidad);
        }
    }

    private boolean hayVacantesEnOtrosCursos() {
        for (Curso curso:((MainActivity) getActivity()).getMateriaSeleccionada().getCursos()) {
            if (curso.getVacantes() != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onSuccess() {
        progressPopup.dismiss();
        // TODO: acá se podría mostrat una tilde verde en vez de grisar el boton
        btnInscribir.setEnabled(false);
        btnInscribir.setBackgroundResource(R.color.gray);

        ((MainActivity) getActivity()).setDesinscripcionesEnabled(true);

        curso.decrementarVacantes();
        vacantes.setText(getString(R.string.vacantes_header, curso.getCupos(), curso.getVacantes()));

    }

    @Override
    public void onError() {
        progressPopup.dismiss();
    }
}
