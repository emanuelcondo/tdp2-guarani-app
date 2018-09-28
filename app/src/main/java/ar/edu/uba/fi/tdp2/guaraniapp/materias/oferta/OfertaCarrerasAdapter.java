package ar.edu.uba.fi.tdp2.guaraniapp.materias.oferta;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ar.edu.uba.fi.tdp2.guaraniapp.MainActivity;
import ar.edu.uba.fi.tdp2.guaraniapp.R;
import ar.edu.uba.fi.tdp2.guaraniapp.materias.Carrera;

public class OfertaCarrerasAdapter extends RecyclerView.Adapter<OfertaCarreraViewHolder> {

    private MainActivity activity;

    public OfertaCarrerasAdapter(Activity activity) {
        this.activity = (MainActivity)activity;
    }

    @NonNull
    @Override
    public OfertaCarreraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OfertaCarreraViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.carrera_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OfertaCarreraViewHolder holder, int position) {
        List<Carrera> carreras = activity.getUsuario().getCarreras();
        Carrera carrera = carreras.get(position);
        holder.bindTo(carrera);
    }

    @Override
    public int getItemCount() {
        return activity.getUsuario().getCarreras().size();
    }
}

