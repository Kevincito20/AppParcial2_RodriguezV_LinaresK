package com.example.appparcial2_rodriguezv_linaresk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class BienvenidaActivity extends AppCompatActivity {

    EditText usuarioNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        InicializarControles();
        ValidarUsuario();
    }

    private void InicializarControles(){
        usuarioNombre = findViewById(R.id.UsuarioNombre);
    }

    public void IniciarSesion(View v){
        String nombreUsuario = usuarioNombre.getText().toString();
        if (!nombreUsuario.isEmpty()){
            Intent intent = new Intent(getApplicationContext(), InicioActivity.class);

            SharedPreferences prefs = getSharedPreferences("Usuario", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("nombre", nombreUsuario);
            editor.putBoolean("loged", true);
            editor.commit();

            startActivity(intent);
        }
    }

    private void ValidarUsuario(){
        SharedPreferences pref = getSharedPreferences("Usuario", MODE_PRIVATE);
        Boolean loged = pref.getBoolean("loged", false);

        if(loged){
            startActivity(new Intent(getApplicationContext(), InicioActivity.class));
        }
    }
}