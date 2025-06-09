package com.example.appparcial2_rodriguezv_linaresk.entidades;

public class Entrenamiento {

    private String distancia;
    private String tiempo;
    private String fecha;
    private String tipoEntrenamiento;
    private String ritmoPromedio;


    public Entrenamiento(String distancia, String tiempo, String fecha, String tipoEntrenamiento, String ritmoPromedio) {
        this.distancia = distancia;
        this.tiempo = tiempo;
        this.fecha = fecha;
        this.tipoEntrenamiento = tipoEntrenamiento;
        this.ritmoPromedio = ritmoPromedio;
    }

    public String getTipoEntrenamiento() {
        return tipoEntrenamiento;
    }

    public void setTipoEntrenamiento(String tipoEntrenamiento) {
        this.tipoEntrenamiento = tipoEntrenamiento;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getRitmoPromedio() {
        return ritmoPromedio;
    }

    public void setRitmoPromedio(String ritmoPromedio) {
        this.ritmoPromedio = ritmoPromedio;
    }
}