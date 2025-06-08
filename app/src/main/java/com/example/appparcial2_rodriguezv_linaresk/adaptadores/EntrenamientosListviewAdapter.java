package com.example.appparcial2_rodriguezv_linaresk.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appparcial2_rodriguezv_linaresk.R;
import com.example.appparcial2_rodriguezv_linaresk.entidades.Entrenamiento;

import java.util.List;

public class EntrenamientosListviewAdapter extends ArrayAdapter<Entrenamiento> {
    private List<Entrenamiento> entrenamientos;

    public EntrenamientosListviewAdapter(@NonNull Context context, @NonNull List<Entrenamiento> objects) {
        super(context, R.layout.listview_historiales, objects);

        this.entrenamientos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_historiales,null);

        TextView lblTipoEntrenamiento = item.findViewById(R.id.lblTipoE);
        lblTipoEntrenamiento.setText(entrenamientos.get(position).getTipoEntrenamiento());

        TextView lblFecha = item.findViewById(R.id.lblFechaE);
        lblFecha.setText(entrenamientos.get(position).getFecha());

        TextView lblDistancia = item.findViewById(R.id.lbldistanciarecorrida);
        lblDistancia.setText(entrenamientos.get(position).getDistancia());

        TextView lblTiempo = item.findViewById(R.id.lbltiempo);
        lblTiempo.setText(entrenamientos.get(position).getTiempo());

        return item;
    }
}