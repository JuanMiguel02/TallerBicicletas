package org.demo.models;

import java.util.ArrayList;

public class Cliente extends Persona {

    private ArrayList<Bicicleta> bicicletas;

    public Cliente(String nombreCompleto, String telefono, String documento, String direccion) {
        super(nombreCompleto, telefono, direccion, documento);
        this.bicicletas =  new ArrayList<>();
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

                ", bicicletas=" + bicicletas +
                '}';
    }
}
