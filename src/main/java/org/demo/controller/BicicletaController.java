package org.demo.controller;

import javafx.collections.ObservableList;
import org.demo.models.Bicicleta;
import org.demo.models.Cliente;
import org.demo.models.Taller;

public class BicicletaController {

    private final Taller taller = Taller.getInstancia();

    public boolean registrarBicicleta(Bicicleta bicicleta){
        return taller.registrarBicicleta(bicicleta);
    }

    public boolean eliminarBicicleta(Bicicleta bicicleta){
        return taller.eliminarBicicleta(bicicleta);
    }

    public ObservableList<Bicicleta> listarBicicletas(){
        return taller.getBicicletas();
    }

    public boolean actualizarBicicleta(Bicicleta bicicleta){
        return taller.actualizarBicicleta(bicicleta);
    }

    public ObservableList<Cliente> obtenerClientes(){
        return taller.getClientes();
    }


}
