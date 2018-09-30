package ar.edu.uba.fi.tdp2.guaraniapp.materias.inscripcion;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.RequestSender;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Materia;

public class InscripcionMateriaViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener  {
    private TextView textViewCodigo;
    private TextView textViewNombre;
    private Materia materia;

    public InscripcionMateriaViewHolder(final View itemView) {
        super(itemView);

        textViewCodigo = itemView.findViewById(R.id.codigo_materia);
        textViewNombre = itemView.findViewById(R.id.nombre_materia);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCursos();
                ((MainActivity) itemView.getContext()).setMateriaSeleccionada(materia);
            }
        });

    }

    private void loadCursos() {
        Context context = itemView.getContext();
        InscripcionCursosListener inscripcionCursosListener = new InscripcionCursosListener(context);
        RequestSender requestSender = new RequestSender(context);

        String url = context.getString(R.string.urlAppServer) + "materias/" + materia.get_id() + "/cursos";

        requestSender.doGet_expectJSONObject(inscripcionCursosListener, url);
    }

    public void bindTo(Materia materia) {

        textViewCodigo.setText(materia.getCodigo());
        textViewNombre.setText(materia.getNombre());
        this.materia = materia;
    }

    @Override
    public void onClick(View view) {

    }

}
