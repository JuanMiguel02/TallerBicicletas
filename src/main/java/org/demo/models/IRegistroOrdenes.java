package org.demo.models;

public interface IRegistroOrdenes {
    boolean registrarOrden(OrdenReparacion orden);
    boolean eliminarOrden(OrdenReparacion orden);
    boolean actualizarOrden(OrdenReparacion orden);

}
