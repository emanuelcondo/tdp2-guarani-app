package ar.edu.uba.fi.tdp2.guaraniapp.historia;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ar.edu.uba.fi.tdp2.guaraniapp.R;

public class HistoriaAcademicaViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener  {

    private TextView textViewCodigoMateria;
    private TextView textViewNombreMateria;
    private TextView textViewNota;
    private TextView textViewFecha;

    public HistoriaAcademicaViewHolder(final View itemView) {
        super(itemView);

        textViewCodigoMateria = itemView.findViewById(R.id.codigo_materia_resultado);
        textViewNombreMateria = itemView.findViewById(R.id.nombre_materia_resultado);
        textViewNota = itemView.findViewById(R.id.nota_resultado);
        textViewFecha = itemView.findViewById(R.id.fecha_resultado);
    }

    public void bindTo(ResultadoExamen resultadoExamen) {

        textViewCodigoMateria.setText(resultadoExamen.getMateria().getCodigo());
        textViewNombreMateria.setText(resultadoExamen.getMateria().getNombre());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String strDate = dateFormat.format(resultadoExamen.getFecha());
        textViewFecha.setText("Fecha: " + strDate);
        String nota = "Nota: " + resultadoExamen.getNota();
        textViewNota.setText(nota);
    }

    @Override
    public void onClick(View view) {

    }

}
