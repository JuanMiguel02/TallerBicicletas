package org.demo.viewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class TallerController {
    @FXML
    private void mostrarInicio() {

    }

    @FXML
    private void OnCliente() {
        cargarVista("/org/demo/Views/ClienteView/TablaClientes-view.fxml");
    }

    @FXML
    private void OnBicicleta() {
        cargarVista("/org/demo/Views/BicicletaView/TablaBicicleta-view.fxml");
    }

    @FXML
    private void OnMecanico() {
        cargarVista("/org/demo/Views/MecanicoView/TablaMecanico-view.fxml");
    }

    @FXML
    private void OnOrdenServicio() {}

    @FXML
    private void OnConsulta() {}

    @FXML
    private void volverLogin() {

    }

    @FXML
     public StackPane contenedorCentro;


    private void cargarVista(String rutaFXML) {
        try {
            Parent vista = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(rutaFXML))
            );
            contenedorCentro.getChildren().clear();
            contenedorCentro.getChildren().add(vista);
        }
         catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
