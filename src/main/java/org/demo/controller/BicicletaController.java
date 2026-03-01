package org.demo.controller;

import org.demo.models.Bicicleta;
import org.demo.models.Taller;

import java.util.List;

public class BicicletaController {

    private final Taller taller = Taller.getInstancia();

    public boolean registrarBicicleta(Bicicleta bicicleta){
        return taller.registrarBicicleta(bicicleta);
    }

    public boolean eliminarBicicleta(Bicicleta bicicleta){
        return taller.eliminarBicicleta(bicicleta);
    }

    public List<Bicicleta> listarBicicletas(){
        return taller.getBicicletas();
    }

    public boolean actualizarBicicleta(Bicicleta bicicleta){
        return taller.actualizarBicicleta(bicicleta);
    }



}
