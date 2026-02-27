package org.demo.controllers.VistasClientes;

import javafx.fxml.FXML;

public class FormularioClienteController {

    @FXML
    private void  guardarCliente (){
        System.out.println("Guardando Cliente");
    }

    @FXML
    private void cancelar(){
        System.out.println("Cancelando ");
    }
}
