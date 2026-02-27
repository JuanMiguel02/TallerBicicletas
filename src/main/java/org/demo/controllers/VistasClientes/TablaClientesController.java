package org.demo.controllers.VistasClientes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TablaClientesController {
    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;


    @FXML
    private TableView<?> tablaClientes;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colIdentificacion;

    @FXML
    private TableColumn<?, ?> colTelefono;

    @FXML
    private TableColumn<?, ?> colDireccion;



    @FXML
    private void onNuevoCliente() {
        try {

            // Cargar formulario
            Parent formulario = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(
                            "/org/demo/Views/ClienteView/FormularioCliente-View.fxml"
                    ))
            );


            StackPane contenedor =
                    (StackPane) tablaClientes.getScene()
                            .lookup("#contenedorCentro");

            // Reemplazar vista
            contenedor.getChildren().setAll(formulario);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditarCliente() {
        System.out.println("Botón Editar Cliente presionado");
    }

    @FXML
    private void onEliminarCliente() {
        System.out.println("Botón Eliminar Cliente presionado");
    }
}

