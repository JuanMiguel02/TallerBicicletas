package org.demo.controller;

import javafx.collections.ObservableList;
import org.demo.models.Cliente;
import org.demo.models.Taller;
import java.util.List;

public class ClienteController {

    private final Taller taller = Taller.getInstancia();

    public boolean registrarCliente(Cliente cliente){
        return taller.registrarCliente(cliente);
    }

    public boolean eliminarCliente(Cliente cliente){
        return taller.eliminarCliente(cliente);
    }

    public ObservableList<Cliente> listarClientes(){
        return taller.getClientes();
    }

    public boolean actualizarCliente(Cliente cliente){
        return taller.actualizarCliente(cliente);
    }


}
