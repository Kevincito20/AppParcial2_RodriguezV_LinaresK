package com.example.appparcial2_rodriguezv_linaresk;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appparcial2_rodriguezv_linaresk.reutilizable.Utilidades;

public class InicioActivity extends AppCompatActivity {

    TextView lblNombre, lblDistanciaMeta, lblProgresoRestante, lblMensaje;
    RelativeLayout layoutProgresoActual, layoutMetaCompletada, layoutBienvenida;
    TextView lblMetaCompletada, lblDistanciaMetaCompletada, lblPorcentajeCompletado, lblPorcentajeFaltante;

    RelativeLayout btnRegistrar, btnHistorial, btnMetas, btnFrases;

    FrasesActivity obj = new FrasesActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_inicio);
        InicializarControles();
        cambiarPantalla();
     //   controlarAccesosPorMeta();
        Utilidades.Volver(this, R.id.imvVolver);

        obj.hover(btnRegistrar,"#2563eb","#1d4ed8","white");
        obj.hover(btnHistorial,"#16a34a","#15803d","white");

        obj.hover(btnMetas,"#a855f7","#7c3aed","white");
        obj.hover(btnFrases,"#f97316","#ea580c","white");

    }

    @Override
    protected void onResume() {
        super.onResume();
        MapearNombre();
        mostrarLayoutProgreso();
      //  controlarAccesosPorMeta();
    }


    private void InicializarControles(){
       // layoutBienvenida = findViewById(R.id.Bienvenida);
        lblMensaje = findViewById(R.id.lblprogresoCompletado);
        lblProgresoRestante = findViewById(R.id.lblprogresoFaltante);
        lblDistanciaMeta = findViewById(R.id.lblDistanciaMeta);
        lblNombre = findViewById(R.id.lblNombreUsuario);
        btnRegistrar = findViewById(R.id.btnRegistrarEntrenamiento);
        btnFrases = findViewById(R.id.btnFrases);
        btnHistorial= findViewById(R.id.btnHistorial);
        btnMetas= findViewById(R.id.btnMetas);
        layoutProgresoActual = findViewById(R.id.progresoMensual);
        layoutMetaCompletada = findViewById(R.id.progeso);
        lblPorcentajeFaltante = findViewById(R.id.PorcentajeFaltante);
        lblMetaCompletada = findViewById(R.id.lbLMetaCompletada);
        lblDistanciaMetaCompletada = findViewById(R.id.lblDistanciaMetaCompletada);
        lblPorcentajeCompletado = findViewById(R.id.PorcentajeCompletado);

        MapearNombre();
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

    private void mostrarLayoutProgreso() {
        SharedPreferences pref = getSharedPreferences("MetaMensual", MODE_PRIVATE);
        float meta = pref.getFloat("Meta", 0);
        float distanciaRecorrida = pref.getFloat("DistanciaRecorrida", 0);

        boolean metaValida = meta > 0;

        if (!metaValida) {
            layoutProgresoActual.setVisibility(View.GONE);
            layoutMetaCompletada.setVisibility(View.GONE);
            return;
        }

        if (distanciaRecorrida >= meta) {
            mostrarMetaCompletada(distanciaRecorrida);
        } else {
            mostrarMetaEnProgreso(distanciaRecorrida, meta);
        }
    }

    private void mostrarMetaCompletada(float distanciaRecorrida) {
        layoutProgresoActual.setVisibility(View.GONE);
        layoutMetaCompletada.setVisibility(View.VISIBLE);

        lblDistanciaMetaCompletada.setText(String.format("%.1f km", distanciaRecorrida));
        lblMetaCompletada.setText("¡Meta completada!");
        lblMensaje.setText("¡Increíble! Completaste tú meta, Sigue así");
        lblPorcentajeCompletado.setText("100%");
    }

    private void mostrarMetaEnProgreso(float distanciaRecorrida, float meta) {
        layoutProgresoActual.setVisibility(View.VISIBLE);
       // layoutBienvenida.setVisibility(View.GONE);
        layoutMetaCompletada.setVisibility(View.GONE);

        float restante = meta - distanciaRecorrida;
        float porcentaje = (distanciaRecorrida / meta) * 100;

        lblDistanciaMeta.setText(String.format("%.1f / %.1f km", distanciaRecorrida, meta));
        lblProgresoRestante.setText(String.format("Te faltan %.1f km para alcanzar tu meta", restante));
        lblPorcentajeFaltante.setText(String.format("%.0f%%", porcentaje));
    }

    /*
    private void controlarAccesosPorMeta() {
        SharedPreferences pref = getSharedPreferences("MetaMensual", MODE_PRIVATE);
        float meta = pref.getFloat("Meta", 0);

        boolean metaEstablecida = meta > 0;

        btnRegistrar.setEnabled(metaEstablecida);
        btnHistorial.setEnabled(metaEstablecida);
        btnFrases.setEnabled(metaEstablecida);

        float alphaDesactivado = 0.4f;
        float alphaActivo = 1f;

        btnRegistrar.setAlpha(metaEstablecida ? alphaActivo : alphaDesactivado);
        btnHistorial.setAlpha(metaEstablecida ? alphaActivo : alphaDesactivado);
        btnFrases.setAlpha(metaEstablecida ? alphaActivo : alphaDesactivado);
    }
    */


}