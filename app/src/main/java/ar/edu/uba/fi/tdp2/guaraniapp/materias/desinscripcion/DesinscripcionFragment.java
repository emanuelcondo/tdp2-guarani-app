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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Inscripcion;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Periodo;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Persona;
import ar.edu.uba.fi.tdp2.guaraniapp.model.Plazo;

public class DesinscripcionFragment extends Fragment implements ResponseWatcher {

    private TextView numeroCurso;
    private TextView docente;
    private TableLayout horarios;
    private TextView vacantes;
    private TextView mensajePeriodo;
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
        mensajePeriodo = rootView.findViewById(R.id.mensaje_periodo_desinscripcion);

        progressPopup = new ProgressPopup("Desinscribiendo...", getContext());

        btnDesinscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressPopup.show();
                desinscribir();
            }
        });

        inscripcion = ((MainActivity) getActivity()).getInscripcionSeleccionada();

        bindCurso();

    }

    private void desinscribir() {
        DesinscripcionListener listener = new DesinscripcionListener(getContext(), this);
        RequestSender requestSender = new RequestSender(getContext());

        String url = getContext().getString(R.string.urlAppServer) + "inscripciones/" + inscripcion.get_id() + "/cursos/";

        requestSender.doDelete(listener, url);
    }

    public void onSuccess() {
        progressPopup.dismiss();
        // TODO: acá se podría mostrat una tilde verde en vez de grisar el boton
        deshabilitarBotonDesinscribir();

        // deshabilito (en caso de no haber mas inscripciones) en el menu la desinscripcion
        ((MainActivity) getActivity()).eliminarInscripcion(inscripcion);

        if (inscripcion.esCondicional()) {
            vacantes.setText("Te has desinscripto de esta materia.");
        } else {
            Curso curso = inscripcion.getCurso();
            curso.agregarVacante();
            vacantes.setText(getString(R.string.vacantes_header, curso.getCupos(), curso.getVacantes()));
        }

    }

    private void deshabilitarBotonDesinscribir() {
        btnDesinscribir.setEnabled(false);
        btnDesinscribir.setBackgroundResource(R.color.gray);
    }

    public void onError() {
        progressPopup.dismiss();
    }

    @Override
    public void onResume() {
        //OnResume se llama siempre despues de OnStart y cada vez que se vuelve al fragment
        super.onResume();
        String materia = ((MainActivity) getActivity()).getInscripcionSeleccionada().getMateria().getNombre();
        ((MainActivity) getActivity()).setToolbarName(materia);
    }

    private void bindCurso() {
        Curso curso;
        modalidades.setVisibility(View.GONE);
        if (inscripcion.esCondicional()) {
            curso = new Curso();
            docente.setText(R.string.condicional);
            horarios.setVisibility(View.GONE);
            vacantes.setText(inscripcion.getFormatedTimestamp());

        } else {
            curso = inscripcion.getCurso();
            docente.setText(curso.getDocente());
            vacantes.setText(getString(R.string.vacantes_header, curso.getCupos(), curso.getVacantes()));
        }

        if (!((MainActivity) getActivity()).esFechaDeDesinscripcionCursos()) {
            deshabilitarBotonDesinscribir();
            mensajePeriodo.setVisibility(View.VISIBLE);
            Plazo plazoDesinscripcion = ((MainActivity) getActivity()).getPeriodo().getDesinscripcionCurso();

            Date dateInicio = null;
            Date dateFin = null;
            try {
                dateInicio = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
                        , Locale.getDefault()).parse(plazoDesinscripcion.getInicio());
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateInicio);
                cal.add(Calendar.HOUR, -3);
                dateInicio = cal.getTime();
                dateFin = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
                        , Locale.getDefault()).parse(plazoDesinscripcion.getFin());
                cal.setTime(dateFin);
                cal.add(Calendar.HOUR, -3);
                dateFin = cal.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String inicio = new SimpleDateFormat("dd/MM/yyyy HH:mm"
                    , Locale.getDefault())
                    .format(dateInicio);
            String fin = new SimpleDateFormat("dd/MM/yyyy HH:mm"
                    , Locale.getDefault())
                    .format(dateFin);

            mensajePeriodo.setText(getString(R.string.periodo_desinscripcion, inicio, fin));
        }

        numeroCurso.setText("Curso " + curso.getComision());



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

            /*TextView modalidad = new TextView(getContext());
            String s_modalidad = horario.getDia() + " " + horario.getHoraInicio() + " - " + horario.getHoraFin() + ": " + horario.getTipo();
            modalidad.setText(s_modalidad);
            modalidad.setPadding(8,8,8,8);
            modalidades.addView(modalidad);*/
        }

    }
}
