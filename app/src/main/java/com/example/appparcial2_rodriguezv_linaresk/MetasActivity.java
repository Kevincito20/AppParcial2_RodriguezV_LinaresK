package com.example.appparcial2_rodriguezv_linaresk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MetasActivity extends AppCompatActivity {

    ProgressBar progressbar;
    EditText editTextKm;

    Button btnGuardar;


    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metas);

        inicializarControles();
        float recorridoTotal = leerRecorridoDesdeArchivo();
        setProgressbar((int) recorridoTotal, false);

    }

    private void inicializarControles() {
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        editTextKm = (EditText) findViewById(R.id.editTextKm);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
    }

    private float leerRecorridoDesdeArchivo() {
        float sumaTotal = 0f;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("nombreArchivo.txt")))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    try {
                        float valor = Float.parseFloat(linea.trim());
                        sumaTotal += valor;
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Dato inválido en el archivo: " + linea, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al leer el archivo: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return sumaTotal;
    }


    public void setProgressbar(int progreso, boolean animate) {
        int minimo = 0;
        int maximo = progressbar.getMax();

        progreso = Math.max(minimo, Math.min(progreso, maximo));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressbar.setProgress(progreso, animate);
        } else {
            progressbar.setProgress(progreso);
        }
    }




    //CONFIGURACION PARA EL MANEJO DEL EDITTEXT
    public void guardarRecorrido(View view){
        String input = editTextKm.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa una meta", Toast.LENGTH_SHORT).show();
            return;
        }

        try {

            float metaMensual = Float.parseFloat(input);
            guardarMeta(metaMensual);

            Toast.makeText(this, "INFORMACIÓN ALMACENADA", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor ingresa un número válido", Toast.LENGTH_SHORT).show();
        }
    }


    private void guardarMeta(float meta){

        try {


            SharedPreferences pref = getSharedPreferences("MetaMensual",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putFloat("Meta",meta);
            editor.commit();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error al almacenar Meta mensual",Toast.LENGTH_LONG).show();

        }
    }




}