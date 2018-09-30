package ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Alumno;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Inscripcion;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Persona;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionListener;

public class DesinscripcionFragment extends Fragment implements ResponseWatcher {

    private TextView numeroCurso;
    private TextView docente;
    private TableLayout horarios;
    private TextView vacantes;
    private Button btnDesinscribir;
    private LinearLayout modalidades;
    private LinearLayout ayudantes;

    private ProgressPopup progressPopup;

    private Inscripcion inscripcion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.desinscripcion, container, false);

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
        btnDesinscribir = rootView.findViewById(R.id.ins_btn_desinscribir);

        btnDesinscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressPopup = new ProgressPopup("Desinscribiendo...", getContext());
                desinscribir();
            }
        });

        inscripcion = ((MainActivity) getActivity()).getInscripcionSeleccionada();

        bindCurso();

    }

    private void desinscribir() {
        DesinscripcionListener desinscripcionListener = new DesinscripcionListener(getContext(), this);
        RequestSender requestSender = new RequestSender(getContext());

        String url = getContext().getString(R.string.urlAppServer) + "inscripciones/" + inscripcion.get_id() + "/cursos/";

        requestSender.doDelete(desinscripcionListener, url);
    }

    public void onSuccess() {
        progressPopup.dismiss();
        // TODO: acá se podría mostrat una tilde verde en vez de grisar el boton
        btnDesinscribir.setEnabled(false);
        btnDesinscribir.setBackgroundResource(R.color.gray);

        //TODO: Esto esta demás, o habria q hacer un notifyDatasetChanged para que se vea la diferencia
        //this.inscripcion.getCurso().decrementarVacantes();


    }

    public void onError() {
        progressPopup.dismiss();
    }

    private void bindCurso() {
        Curso curso;
        if (inscripcion.esCondicional()) {
            curso = new Curso();
            docente.setText(R.string.condicional);
            horarios.setVisibility(View.GONE);
            modalidades.setVisibility(View.GONE);
        } else {
            curso = inscripcion.getCurso();
            docente.setText(curso.getDocente());
        }

        numeroCurso.setText("Curso " + curso.getComision());

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
            textViewHorario.setText(getContext().getString(R.string.horario_row, horario.getHoraInicio(), horario.getHoraFin()));
            textViewHorario.setBackgroundResource(R.drawable.cell_shape);
            textViewHorario.setPadding(8,8,8,8);

            TextView textViewSede = new TextView(getContext());
            textViewSede.setText(curso.getSede().getNombre());
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
