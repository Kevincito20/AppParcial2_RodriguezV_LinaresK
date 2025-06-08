package com.example.appparcial2_rodriguezv_linaresk;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.appparcial2_rodriguezv_linaresk.adaptadores.EntrenamientosListviewAdapter;
import com.example.appparcial2_rodriguezv_linaresk.entidades.Entrenamiento;
import com.example.appparcial2_rodriguezv_linaresk.reutilizable.Utilidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HistorialActivity extends AppCompatActivity {

    ListView lstEntrenamientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        InicializarControles();
        Utilidades.Volver(this, R.id.imvVolver);
    }

    private void InicializarControles(){
        lstEntrenamientos = findViewById(R.id.lstHistorialEntrenamientos);
        llenarListView();
    }

    private String LeerArchivo() {
        StringBuilder lectura = new StringBuilder();

        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(openFileInput("entrenamientos.txt")));
            String linea;

            while ((linea = bfr.readLine()) != null) {
                lectura.append(linea);
            }

            bfr.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error en lectura: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return lectura.toString();
    }


    private void llenarListView() {
        List<Entrenamiento> entrenamientos = new ArrayList<>();
        String datos = this.LeerArchivo();

        if (datos == null || datos.isEmpty()) {
            Toast.makeText(this, "No hay datos para mostrar", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] rawEntrenamientos = datos.split("\\?");

        for (String raw : rawEntrenamientos) {
            String[] campos = raw.split("\\|");
            if (campos.length < 4) continue;

            Entrenamiento entrenamiento = new Entrenamiento(
                    campos[0],
                    campos[1],
                    campos[2],
                    campos[3]
            );
            entrenamientos.add(entrenamiento);
        }

        EntrenamientosListviewAdapter adapter = new EntrenamientosListviewAdapter(this, entrenamientos);
        lstEntrenamientos.setAdapter(adapter);
    }



}