package com.example.appparcial2_rodriguezv_linaresk;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appparcial2_rodriguezv_linaresk.reutilizable.Utilidades;

public class InicioActivity extends AppCompatActivity {

    TextView lblNombre;
    RelativeLayout btnRegistrar, btnHistorial, btnMetas, btnFrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_inicio);
        InicializarControles();
        MapearNombre();
        cambiarPantalla();

    }

    private void InicializarControles(){
        lblNombre = findViewById(R.id.lblNombreUsuario);
        btnRegistrar = findViewById(R.id.btnRegistrarEntrenamiento);
        btnFrases = findViewById(R.id.btnFrases);
        btnHistorial= findViewById(R.id.btnHistorial);
        btnMetas= findViewById(R.id.btnMetas);
    }
    private void MapearNombre(){
        SharedPreferences pref = getSharedPreferences("Usuario", MODE_PRIVATE);
        lblNombre.setText(pref.getString("nombre", ""));
    }
    public void cambiarPantalla(){
        Utilidades.Botones(btnRegistrar,this,RegistrarEntrenamientoActivity.class);
        Utilidades.Botones(btnFrases,this,FrasesActivity.class);
        Utilidades.Botones(btnHistorial,this,HistorialActivity.class);
        Utilidades.Botones(btnMetas,this,MetasActivity.class);
    }

}