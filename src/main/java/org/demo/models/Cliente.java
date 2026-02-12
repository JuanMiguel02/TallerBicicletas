package org.demo.models;

import java.util.ArrayList;

public class Cliente extends Persona {

    private String direccion;
    private ArrayList<Bicicleta> bicicletas;

    public Cliente(String nombreCompleto, String telefono, String documento, String direccion) {
        super(nombreCompleto, telefono, documento);
        this.direccion = direccion;
        this.bicicletas =  new ArrayList<>();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Bicicleta> getBicicletas() {
        return bicicletas;
    }

    public void setBicicletas(ArrayList<Bicicleta> bicicletas) {
        this.bicicletas = bicicletas;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "direccion='" + direccion + '\'' +
                ", bicicletas=" + bicicletas +
                '}';
    }
}
