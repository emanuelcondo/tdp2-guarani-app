package ar.edu.uba.fi.tdp2.guaraniapp.examenes.inscripcion;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ConfirmationPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.ProgressPopup;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.Examen;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion.InscripcionListener;

public class InscripcionExamenViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewNumeroCurso;
    private TextView textViewDocente;
    private TableLayout tableHorarioExamen;

    private AppCompatButton botonRegular;
    private AppCompatButton botonLibre;

    private Examen examen;

    public InscripcionExamenViewHolder(final View itemView) {
        super(itemView);

        textViewNumeroCurso = itemView.findViewById(R.id.fecha_examen_codigo_curso);
        textViewDocente = itemView.findViewById(R.id.fecha_examen_nombre_curso);
        tableHorarioExamen = itemView.findViewById(R.id.tabla_horario_examen);

        botonRegular = itemView.findViewById(R.id.anotarse_regular_examen_boton);
        botonLibre = itemView.findViewById(R.id.anotarse_libre_examen_boton);

        botonRegular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new InscripcionExamenRegularWatcher(examen, itemView.getContext());
            }
        });
        botonLibre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new InscripcionExamenLibreWatcher(examen, itemView.getContext());
            }
        });
    }

    public void bindTo(Examen examen) {

        Curso curso = examen.getCurso();
        Materia materia = examen.getMateria();

        textViewDocente.setText(curso.getDocente());
        textViewNumeroCurso.setText(itemView.getContext().getString(R.string.curso_header, curso.getComision()));
        this.examen = examen;

        TableRow header = new TableRow(itemView.getContext());

        TextView textViewHeaderFecha = new TextView(itemView.getContext());
        textViewHeaderFecha.setText(R.string.horario_examen_header);
        textViewHeaderFecha.setTextSize(16);
        textViewHeaderFecha.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHeaderFecha.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderFecha.setPadding(8,8,8,8);

        TextView textViewHeaderSede = new TextView(itemView.getContext());
        textViewHeaderSede.setText(R.string.sede_header);
        textViewHeaderSede.setTextSize(16);
        textViewHeaderSede.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHeaderSede.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderSede.setPadding(8,8,8,8);

        TextView textViewHeaderAula = new TextView(itemView.getContext());
        textViewHeaderAula.setText(R.string.aula_header);
        textViewHeaderAula.setTextSize(16);
        textViewHeaderAula.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        textViewHeaderAula.setBackgroundResource(R.color.colorPrimary);
        textViewHeaderAula.setPadding(8,8,8,8);

        header.addView(textViewHeaderFecha);
        header.addView(textViewHeaderSede);
        header.addView(textViewHeaderAula);

        tableHorarioExamen.addView(header);

        TableRow row = new TableRow(itemView.getContext());

        TextView textViewFecha = new TextView(itemView.getContext());
        textViewFecha.setText(examen.getFecha());
        textViewFecha.setBackgroundResource(R.drawable.cell_shape);
        textViewFecha.setPadding(8,8,8,8);

        TextView textViewSede = new TextView(itemView.getContext());
        textViewSede.setText(examen.getAula().getSede());
        textViewSede.setBackgroundResource(R.drawable.cell_shape);
        textViewSede.setPadding(8,8,8,8);

        TextView textViewAula = new TextView(itemView.getContext());
        textViewAula.setText(examen.getAula().getAula());
        textViewAula.setBackgroundResource(R.drawable.cell_shape);
        textViewAula.setPadding(8,8,8,8);

        row.addView(textViewFecha);
        row.addView(textViewSede);
        row.addView(textViewAula);

        tableHorarioExamen.addView(row);
    }
}

