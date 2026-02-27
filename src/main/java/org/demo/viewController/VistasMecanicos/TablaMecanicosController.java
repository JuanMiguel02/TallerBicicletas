package org.demo.viewController.VistasMecanicos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class TablaMecanicosController {
    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;


    @FXML
    private TableView<?> tablaMecanicos;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colEspecialidad;

    @FXML
    private TableColumn<?, ?> colCertificacion;





    @FXML
    private void onNuevoMecanico() {
        try {

            // Cargar formulario
            Parent formulario = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(
                            "/org/demo/Views/MecanicoView/FormularioMecanico-view.fxml"
                    ))
            );


            StackPane contenedor =
                    (StackPane) tablaMecanicos.getScene()
                            .lookup("#contenedorCentro");

            // Reemplazar vista
            contenedor.getChildren().setAll(formulario);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditarMecanico() {
        System.out.println("Botón Editar Mecanico presionado");
    }

    @FXML
    private void onEliminarMecanico() {
        System.out.println("Botón Eliminar Mecanico presionado");
    }
}
