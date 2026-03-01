package org.demo.controller;

import javafx.collections.ObservableList;
import org.demo.models.Bicicleta;
import org.demo.models.Cliente;
import org.demo.models.OrdenReparacion;
import org.demo.models.Taller;

public class TallerController {

    private final Taller taller = Taller.getInstancia();

    public ObservableList<Cliente> listarClientes(){
        return taller.getClientes();
    }

    public ObservableList<Bicicleta> listarBicicletas(){
        return taller.getBicicletas();
    }

    public ObservableList<OrdenReparacion> listarOrdenes(){
        return taller.getReparaciones();
    }
}
