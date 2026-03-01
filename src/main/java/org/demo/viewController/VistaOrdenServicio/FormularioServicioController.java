package org.demo.viewController.VistaOrdenServicio;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FormularioServicioController {

    @FXML
    private Button btnCancelar;
    @FXML
    private void onGuardarOrden(){
        System.out.println("Orden Guardada Correctamente");
    }

    @FXML
    private void onCancelar(){
        System.out.println("Cacelando");
    }
}
