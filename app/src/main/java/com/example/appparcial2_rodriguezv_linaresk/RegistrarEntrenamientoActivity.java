package com.example.appparcial2_rodriguezv_linaresk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appparcial2_rodriguezv_linaresk.entidades.Entrenamiento;
import com.example.appparcial2_rodriguezv_linaresk.reutilizable.Utilidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RegistrarEntrenamientoActivity extends AppCompatActivity {

    EditText txtDistancia, txtTiempo;
    Spinner spnTipoEntrenamiento;
    DatePicker dtpFecha;

    private String archivo = "entrenamientos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_entrenamiento);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        InicicializarControles();
        Utilidades.Volver(this, R.id.imvVolver);
    }

    private void InicicializarControles(){
        txtDistancia = findViewById(R.id.txtDistanciaRecorridaEntrenamiento);
        txtTiempo = findViewById(R.id.txtTiempoEntrenamiento);
        spnTipoEntrenamiento = findViewById(R.id.spnTipoEntrenamiento);
        this.llenarSpinner();
        dtpFecha = findViewById(R.id.dtp_FechaEntrenamiento);
    }

    private void llenarSpinner(){
        List<String> tipoEntrenamiento = new ArrayList<>();
        tipoEntrenamiento.add("fondo");
        tipoEntrenamiento.add("series");
        tipoEntrenamiento.add("trote");
        tipoEntrenamiento.add("carrera");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                tipoEntrenamiento
        );
        spnTipoEntrenamiento.setAdapter(adapter);
    }

    public void GuardarEntrenamiento(View v){
        Entrenamiento entrenamiento = new Entrenamiento(
                txtDistancia.getText().toString(),
                txtTiempo.getText().toString(),
                obtenerFecha(),
                spnTipoEntrenamiento.getSelectedItem().toString()
        );

        Toast.makeText(getApplicationContext(),"Entrenamiento Guardado Correctamente", Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(),InicioActivity.class);
        CrearArchivo(
                entrenamiento.getFecha() + "|" +
                        entrenamiento.getTipoEntrenamiento() + "|" +
                        entrenamiento.getTiempo() + "|" +
                        entrenamiento.getDistancia()
        );


        startActivity(new Intent(getApplicationContext(), InicioActivity.class));
    }

    private String obtenerFecha() {
        int dia = dtpFecha.getDayOfMonth();
        int mes = dtpFecha.getMonth() + 1;
        int anio = dtpFecha.getYear();

        return String.format(Locale.getDefault(), "%02d/%02d/%04d", dia, mes, anio);
    }


    private void CrearArchivo(String entrenamiento){
        try {
            String datosExistentes = this.LeerArchivo();
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(archivo, Context.MODE_PRIVATE));

            writer.write(entrenamiento+"?"+datosExistentes);
            writer.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Errorcito Escritura => "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private String LeerArchivo(){
        String lectura = "";

        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(openFileInput(archivo)));
            lectura = bfr.readLine();
            bfr.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Errorcito Lectura => "+e.getMessage(),Toast.LENGTH_LONG).show();
        }

        return lectura;

    }

}