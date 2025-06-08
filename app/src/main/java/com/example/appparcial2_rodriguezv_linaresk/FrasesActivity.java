package com.example.appparcial2_rodriguezv_linaresk;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FrasesActivity extends AppCompatActivity {


    TextView textViewFrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frases);
        inicializarControles();

    }


    private void inicializarControles(){
        textViewFrase = (TextView) findViewById(R.id.textViewFrase);

    }
    public void desplegarInformacion(View view){
        Dialog dialog = new Dialog(FrasesActivity.this);
        dialog.setContentView(R.layout.dialog_template);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        // BUSCAR EL ID DEL XML
        TextView txtDialogFrase = dialog.findViewById(R.id.textViewFrase);

        //NUEVO ARRAY Y UTILIZAR INDEX PARA ELEGIR UNO RANDOM
        List<String> frases = frasesMotivadoras();
        int index = (int) (Math.random() * frases.size());
        String fraseAleatoria = frases.get(index);

        txtDialogFrase.setText(fraseAleatoria);

        dialog.show();

    }

    public List<String> frasesMotivadoras(){

        List<String> frases = new ArrayList<>();

        frases.add("El éxito es la suma de pequeños esfuerzos repetidos cada día.");
        frases.add("Cree en ti mismo y todo será posible.");
        frases.add("Cada día es una nueva oportunidad para cambiar tu vida.");
        frases.add("Nunca es tarde para comenzar de nuevo.");
        frases.add("Las grandes cosas nunca vienen de la zona de confort.");
        frases.add("El fracaso es solo la oportunidad de comenzar de nuevo con más experiencia.");
        frases.add("No sueñes tu vida, vive tus sueños.");
        frases.add("La disciplina es el puente entre tus metas y tus logros.");
        frases.add("Si puedes soñarlo, puedes hacerlo.");
        frases.add("El único límite para lograrlo eres tú mismo.");
        frases.add("No puedes romper algo que ya esta rompido.");

        return frases;
    }


}