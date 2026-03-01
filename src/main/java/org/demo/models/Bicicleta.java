package org.demo.models;

public class Bicicleta {

    private String marca;
    private TipoBicicleta tipoBicicleta;
    private String color;
    private String numeroSerie;
    private Cliente propietario;

    public Bicicleta(String marca, TipoBicicleta tipoBicicleta, String color, String numeroSerie, Cliente propietario) {
        this.marca = marca;
        this.tipoBicicleta = tipoBicicleta;
        this.color = color;
        this.numeroSerie = numeroSerie;
        this.propietario = propietario;
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
}
