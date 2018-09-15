package ar.edu.uba.fi.tdp2.guaraniapp.materias;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.RecyclerFragment;


public class MateriasFragment extends RecyclerFragment {


    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected void configureAdapter() {
        MateriasAdapter materiasAdapter = new MateriasAdapter(/*Lista de materias,*/ getActivity());
        this.setConfiguredAdapter(materiasAdapter);
    }

    /*
    @Override
    protected void hacerAlgo() {

        final EditText editTextMateriaElegida = new EditText(this.getActivity());
        editTextMateriaElegida.setMaxLines(1);
        editTextMateriaElegida.setInputType(InputType.TYPE_CLASS_TEXT);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("Agregar inscripcion");
        builder.setView(editTextMateriaElegida);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                agregarInscripcion(editTextMateriaElegida.getText().toString());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //No hacer nada
            }
        });
        builder.show();
    }
    */

    private void agregarMateria(String parametro) {
        //Perfil.agregarInscripcion( new Inscripcion(parametro) );
        this.getAdapter().notifyDataSetChanged();
    }
}

