package org.demo.controller;

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

    public List<OrdenReparacion> listarOrdenes(){
        return taller.getReparaciones();
    }

    public List<Cliente> listarClientes(){
        return taller.getClientes();
    }

    public List<Bicicleta> listarBicicletas(){
        return taller.getBicicletas();
    }

    public List<Mecanico> listarMecanicos(){
        return taller.getMecanicos();
    }

}
