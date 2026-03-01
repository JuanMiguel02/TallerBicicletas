package org.demo.controller;

import javafx.collections.ObservableList;
import org.demo.models.*;

import java.util.List;

public class OrdenServicioController {

    private final Taller taller = Taller.getInstancia();

    public boolean registrarOrden(OrdenReparacion orden){
        return taller.registrarOrden(orden);
    }

    public boolean eliminarOrden(OrdenReparacion orden){
        return taller.eliminarOrden(orden);
    }

    public boolean actualizarOrden(OrdenReparacion orden){
        return taller.actualizarOrden(orden);
    }

    public ObservableList<OrdenReparacion> listarOrdenes(){
        return taller.getReparaciones();
    }

    public ObservableList<Cliente> listarClientes(){
        return taller.getClientes();
    }

    public ObservableList<Bicicleta> listarBicicletas(){
        return taller.getBicicletas();
    }

    public ObservableList<Mecanico> listarMecanicos(){
        return taller.getMecanicos();
    }

}
