package org.demo.viewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TallerController {
    @FXML
    private BorderPane root;
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
    private void OnOrdenServicio() {
        cargarVista("/org/demo/Views/OrdenServicioView/TablaOrdenServicio-view.fxml");
    }

    @FXML
    private void OnConsulta() {
        cargarVista("/org/demo/Views/OrdenServicioView/ConsultaPorFecha-view.fxml");
    }

    @FXML
    private void volverLogin() {
        try {

            Parent login = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(
                            "/org/demo/hello-view.fxml"
                    ))
            );

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(login));
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
     public StackPane contenedorCentro;


    public void cerrar() {
        System.exit(0);
    }

    public void minimizar() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);

    }


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
