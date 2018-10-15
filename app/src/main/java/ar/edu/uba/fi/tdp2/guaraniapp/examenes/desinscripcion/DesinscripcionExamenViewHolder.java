package ar.edu.uba.fi.tdp2.guaraniapp.examenes.desinscripcion;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.FragmentLoader;
import ar.edu.uba.fi.tdp2.guaraniapp.examenes.InscripcionExamen;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Curso;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Horario;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.desinscripcion.DesinscripcionFragment;

public class DesinscripcionExamenViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener  {
    private TextView textViewCodigoMateria;
    private TextView textViewNombreMateria;
    private TextView textViewNumeroCurso;
    private TextView textViewDocente;
    private TextView textViewFechaInscripcion;

    private InscripcionExamen inscripcionExamen;

    private MainActivity activity;

    public DesinscripcionExamenViewHolder(View itemView, MainActivity activity) {
        super(itemView);

        textViewCodigoMateria = itemView.findViewById(R.id.insc_examen_codigo_materia);
        textViewNombreMateria = itemView.findViewById(R.id.insc_examen_materia);
        textViewNumeroCurso = itemView.findViewById(R.id.insc_examen_nro_curso);
        textViewDocente = itemView.findViewById(R.id.insc_examen_docente);
        textViewFechaInscripcion = itemView.findViewById(R.id.insc_examen_fecha_inscripcion);

        textViewCodigoMateria.setOnClickListener(this);
        textViewDocente.setOnClickListener(this);
        textViewNombreMateria.setOnClickListener(this);
        textViewFechaInscripcion.setOnClickListener(this);
        textViewNumeroCurso.setOnClickListener(this);

        itemView.setOnClickListener(this);

        this.activity = activity;
    }

    public void bindTo(InscripcionExamen inscripcionExamen) {
        this.inscripcionExamen = inscripcionExamen;
        bindViews();
    }

    private void bindViews() {

        Curso curso;

        curso = inscripcionExamen.getFechaExamen().getCurso();
        textViewDocente.setText(curso.getDocente());



        textViewCodigoMateria.setText(curso.getMateria().getCodigo());

        textViewNombreMateria.setText(curso.getMateria().getNombre());

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(inscripcionExamen.getTimestamp());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR, -3);
            date = cal.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(date);
        String fechaInscripcion = "Fecha de Inscripción: " + formattedDate;
        textViewFechaInscripcion.setText(fechaInscripcion);

        textViewNumeroCurso.setText(itemView.getContext().getString(R.string.curso_header, curso.getComision()));



    }

    @Override
    public void onClick(View view) {

        Log.d("ClicCursoViewHolder", "Clic en examen");

        activity.setInscripcionExamenSeleccionada(inscripcionExamen);
        FragmentLoader.load(activity, new DesinscripcionFragment(), FragmentLoader.DesinscripcionCurso);
    }

}
