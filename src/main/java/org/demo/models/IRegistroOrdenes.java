package org.demo.models;

import org.demo.controller.OrdenServicioController;

public interface IRegistroOrdenes {
    boolean registrarOrden(OrdenReparacion orden);
    boolean eliminarOrden(OrdenReparacion orden);
    boolean actualizarOrden(OrdenReparacion orden);

}
