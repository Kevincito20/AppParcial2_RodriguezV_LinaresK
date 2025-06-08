package com.example.appparcial2_rodriguezv_linaresk.reutilizable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Utilidades {

    public static void Volver(Activity activity, int imageViewId) {
        ImageView imgVolver = activity.findViewById(imageViewId);
        if (imgVolver != null) {
            imgVolver.setOnClickListener(v -> activity.finish());
        }
    }

    public static void mostrarToast(Context context, String mensaje) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }

    public static void Botones(RelativeLayout boton, Context contexto, Class<?> destino) {
        boton.setOnClickListener(v -> {
            Intent intent = new Intent(contexto, destino);
            contexto.startActivity(intent);
        });
    }

}