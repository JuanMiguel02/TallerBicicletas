package org.demo.controllers.VistasBicicletas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class TablaBicicletasController {
    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;


    @FXML
    private TableView<?> tablaBicicletas;

    @FXML
    private TableColumn<?, ?> colMarca;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private TableColumn<?, ?> colColor;

    @FXML
    private TableColumn<?, ?> colSerial;

    @FXML
    private TableColumn<?, ?> colAnio;



    @FXML
    private void onNuevaBicicleta() {
        try {

            // Cargar formulario
            Parent formulario = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(
                            "/org/demo/Views/BicicletaView/FormularioBicicleta-view.fxml"
                    ))
            );


            StackPane contenedor =
                    (StackPane) tablaBicicletas.getScene()
                            .lookup("#contenedorCentro");

            // Reemplazar vista
            contenedor.getChildren().setAll(formulario);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditarBicicleta() {
        System.out.println("Botón Editar Bicicleta presionado");
    }

    @FXML
    private void onEliminarBicicleta() {
        System.out.println("Botón Eliminar Bicicleta presionado");
    }

    @FXML
    private void onHistorialServicio() {
        System.out.println("Botón Historial de Servicios  presionado");
    }

}
