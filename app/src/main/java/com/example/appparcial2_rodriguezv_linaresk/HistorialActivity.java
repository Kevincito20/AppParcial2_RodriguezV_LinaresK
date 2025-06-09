package com.example.appparcial2_rodriguezv_linaresk;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appparcial2_rodriguezv_linaresk.adaptadores.EntrenamientosListviewAdapter;
import com.example.appparcial2_rodriguezv_linaresk.entidades.Entrenamiento;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HistorialActivity extends AppCompatActivity {

    ListView lstEntrenamientos;
    TextView lblRitmoPromedio, lblKmTotales, lblCantEntrenamientos;
    List<Entrenamiento> entrenamientos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        InicializarControles();
        MostrarEstadisticasGenerales();
    }

    private void InicializarControles(){
        lstEntrenamientos = findViewById(R.id.lstHistorialEntrenamientos);
        lblCantEntrenamientos = findViewById(R.id.lblCantEntrenamientos);
        lblKmTotales = findViewById(R.id.lblKmTotales);
        lblRitmoPromedio = findViewById(R.id.lblRitmoPromedio);
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

        String datos = this.LeerArchivo();

        if (datos == null || datos.isEmpty()) {
            Toast.makeText(this, "No hay datos para mostrar", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] rawEntrenamientos = datos.split("\\?");

        for (String raw : rawEntrenamientos) {
            String[] campos = raw.split("\\|");
            if (campos.length < 5) continue;

            Entrenamiento entrenamiento = new Entrenamiento(
                    campos[1],
                    campos[2],
                    campos[3],
                    campos[0],
                    campos[4]
            );
            entrenamientos.add(entrenamiento);

        }

        EntrenamientosListviewAdapter adapter = new EntrenamientosListviewAdapter(this, entrenamientos);
        lstEntrenamientos.setAdapter(adapter);
    }

    private void MostrarEstadisticasGenerales() {
        int totalEntrenamientos = 0;
        double totalKm = 0;
        double totalMin = 0;

        for (Entrenamiento ent : entrenamientos) {
            totalEntrenamientos++;

            try {
                double km = Double.parseDouble(ent.getDistancia());
                totalKm += km;

                double min = Double.parseDouble(ent.getTiempo());
                totalMin += min;

            } catch (NumberFormatException e) {
                e.getMessage();
            }
        }
        double promedioMinPorKm = totalKm > 0 ? totalMin / totalKm : 0;

        lblCantEntrenamientos.setText(String.valueOf(totalEntrenamientos));
        lblKmTotales.setText(String.format("%.2f", totalKm));
        lblRitmoPromedio.setText(String.format("%.2f", promedioMinPorKm));

    }




}