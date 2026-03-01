package org.demo.models;

public interface IRegistroClientes {

    boolean registrarCliente(Cliente cliente);
    boolean eliminarCliente(Cliente cliente);
    boolean actualizarCliente(Cliente cliente);
    boolean existeCliente(String documento);

}
