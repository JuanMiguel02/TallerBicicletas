package org.demo.viewController.VistaOrdenServicio;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class TablaOrdenServicioController {
    @FXML
    private TableView<?> tablaOrdenes;

    @FXML
    private TableColumn<?, ?> colNumero;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colHora;

    @FXML
    private TableColumn<?, ?> colCliente;

    @FXML
    private TableColumn<?, ?> colBicicleta;

    @FXML
    private TableColumn<?, ?> colEstado;


    @FXML
    private void onEliminarOrden(){
        System.out.println("Eliminando");
    }
    @FXML
    private void onNuevaOrden(){
        try {

            Parent formulario = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(
                            "/org/demo/Views/OrdenServicioView/FormularioOrdenServicio-View.fxml"
                    ))
            );

            StackPane contenedor =
                    (StackPane) tablaOrdenes.getScene()
                            .lookup("#contenedorCentro");

            contenedor.getChildren().setAll(formulario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onVerDetalle(){
        try {

            Parent formulario = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(
                            "/org/demo/Views/OrdenServicioView/DetalleOrden-view.fxml"
                    ))
            );

            StackPane contenedor =
                    (StackPane) tablaOrdenes.getScene()
                            .lookup("#contenedorCentro");

            contenedor.getChildren().setAll(formulario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
