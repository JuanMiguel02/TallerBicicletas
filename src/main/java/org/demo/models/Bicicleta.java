package org.demo.models;

import java.time.Year;

public class Bicicleta {

    private String marca;
    private TipoBicicleta tipoBicicleta;
    private String color;
    private String numeroSerie;
    private Cliente propietario;
    private Year anio;

    public Bicicleta(String marca, TipoBicicleta tipoBicicleta, String color, String numeroSerie, Cliente propietario, Year anio) {
        this.marca = marca;
        this.tipoBicicleta = tipoBicicleta;
        this.color = color;
        this.numeroSerie = numeroSerie;
        this.propietario = propietario;
        this.anio = anio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public TipoBicicleta getTipoBicicleta() {
        return tipoBicicleta;
    }

    public void setTipoBicicleta(TipoBicicleta tipoBicicleta) {
        this.tipoBicicleta = tipoBicicleta;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public void setPropietario(Cliente propietario) {
        this.propietario = propietario;
    }

    public Year getAnio() {
        return anio;
    }

    public void setAnio(Year anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "marca='" + marca + '\'' +
                ", tipoBicicleta=" + tipoBicicleta +
                ", color='" + color + '\'' +
                ", anio=" + anio +
                '}';
    }
}
