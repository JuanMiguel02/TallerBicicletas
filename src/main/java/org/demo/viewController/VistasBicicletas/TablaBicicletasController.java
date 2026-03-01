package org.demo.viewController.VistasBicicletas;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.demo.controller.BicicletaController;
import org.demo.models.Bicicleta;

import java.util.Objects;

import static org.demo.services.ServicioAlerta.mostrarAlerta;
import static org.demo.services.ServicioAlerta.mostrarAlertaError;

public class TablaBicicletasController {

    private final BicicletaController bicicletaController = new BicicletaController();
    private Bicicleta bicicletaSeleccionada;

    @FXML
    private TableView<Bicicleta> tablaBicicletas;

    @FXML
    private TableColumn<Bicicleta, String> colMarca;

    @FXML
    private TableColumn<Bicicleta, String> colTipo;

    @FXML
    private TableColumn<Bicicleta, String> colColor;

    @FXML
    private TableColumn<Bicicleta, String> colSerial;

    @FXML
    private TableColumn<Bicicleta, Number> colAnio;

    @FXML
    private TableColumn<Bicicleta, String> colPropietario;

    @FXML
    public void initialize() {
        inicializarTabla();
        cargarBicicletas();
    }

    private void inicializarTabla() {

        colMarca.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMarca()));
        colTipo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTipoBicicleta().toString()));
        colColor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getColor()));
        colSerial.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNumeroSerie()));
        colAnio.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getAnio().getValue()));
        colPropietario.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPropietario().toString()));
    }

    private void cargarBicicletas() {
        tablaBicicletas.setItems(bicicletaController.listarBicicletas());
    }

    @FXML
    private void onNuevaBicicleta() {
        try {

            Parent formulario = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(
                            "/org/demo/Views/BicicletaView/FormularioBicicleta-view.fxml"
                    ))
            );

            StackPane contenedor =
                    (StackPane) tablaBicicletas.getScene()
                            .lookup("#contenedorCentro");

            contenedor.getChildren().setAll(formulario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onEditarBicicleta() {

        bicicletaSeleccionada = tablaBicicletas.getSelectionModel().getSelectedItem();

        if (bicicletaSeleccionada == null) {
            mostrarAlertaError("Por favor seleccione una bicicleta para actualizar");
            return;
        }

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/org/demo/Views/BicicletaView/FormularioBicicleta-view.fxml"
                    )
            );

            Parent formulario = loader.load();

            FormularioBicicletasController controller = loader.getController();
            controller.setBicicleta(bicicletaSeleccionada);

            StackPane contenedor = (StackPane) tablaBicicletas.getScene().lookup("#contenedorCentro");

            contenedor.getChildren().setAll(formulario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onEliminarBicicleta() {

        bicicletaSeleccionada = tablaBicicletas.getSelectionModel().getSelectedItem();

        if (bicicletaSeleccionada == null) {
            mostrarAlertaError("Por favor seleccione una bicicleta para eliminar");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro que desea eliminar esta bicicleta?");
        confirmacion.setContentText("Bicicleta " + bicicletaSeleccionada.getMarca() + " - Serial: " + bicicletaSeleccionada.getNumeroSerie());

        confirmacion.showAndWait().ifPresent(respuesta -> {

            if (respuesta == ButtonType.OK) {

                if (bicicletaController.eliminarBicicleta(bicicletaSeleccionada)) {
                    mostrarAlerta("Éxito", "Bicicleta eliminada exitosamente", Alert.AlertType.INFORMATION);}
                    cargarBicicletas();
            }
        });
    }

    @FXML
    private void onHistorialServicio() {

        Bicicleta seleccionada = tablaBicicletas.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarAlertaError("Seleccione una bicicleta primero");
            return;
        }

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/org/demo/Views/BicicletaView/HistorialBicicleta-view.fxml"
                    )
            );

            Parent root = loader.load();

            HistorialBicicletaController controller =
                    loader.getController();

            controller.setBicicleta(seleccionada);

            Stage stage = new Stage();
            stage.setTitle("Historial de Servicios");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}