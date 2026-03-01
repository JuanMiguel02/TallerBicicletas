package org.demo.viewController.VistaOrdenServicio;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import org.demo.controller.OrdenServicioController;
import org.demo.models.EstadoOrden;
import org.demo.models.OrdenReparacion;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.demo.services.ServicioAlerta.mostrarAlerta;
import static org.demo.services.ServicioAlerta.mostrarAlertaError;

public class TablaOrdenServicioController {

    private final OrdenServicioController ordenController = new OrdenServicioController();
    private ObservableList<OrdenReparacion> listaOrdenes;
    private OrdenReparacion ordenSeleccionada;

    @FXML
    private TableView<OrdenReparacion> tablaOrdenes;

    @FXML
    private TableColumn<OrdenReparacion, String> colNumero;

    @FXML
    private TableColumn<OrdenReparacion, String> colCliente;

    @FXML
    private TableColumn<OrdenReparacion, String> colBicicleta;

    @FXML
    private TableColumn<OrdenReparacion, String> colMecanico;

    @FXML
    private TableColumn<OrdenReparacion, String> colMotivo;

    @FXML
    private TableColumn<OrdenReparacion, String> colFecha;

    @FXML
    private TableColumn<OrdenReparacion, Number> colCosto;

    @FXML
    private TableColumn<OrdenReparacion, String> colEstado;

    @FXML
    public void initialize() {
        inicializarTabla();
        cargarOrdenes();
    }

    private void inicializarTabla() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        colNumero.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getId().toString()));

        colCliente.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getCliente().getNombreCompleto()));

        colBicicleta.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getBicicleta().getNumeroSerie()));

        colMecanico.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getMecanico().getNombreCompleto()));

        colMotivo.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getMotivo().toString()));

        colFecha.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getFechaYHora().format(formatter)));

        colCosto.setCellValueFactory(cell ->
                new SimpleIntegerProperty(cell.getValue().getCosto()));

        colEstado.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getEstado().toString()));
    }

    private void cargarOrdenes() {
        listaOrdenes = FXCollections.observableArrayList(ordenController.listarOrdenes());
        tablaOrdenes.setItems(listaOrdenes);
    }

    @FXML
    private void onNuevaOrden() {
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
    private void onEliminarOrden() {

        ordenSeleccionada = tablaOrdenes.getSelectionModel().getSelectedItem();

        if (ordenSeleccionada == null) {
            mostrarAlertaError("Por favor seleccione una orden para eliminar");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Desea eliminar esta orden?");
        confirmacion.setContentText("Orden ID: " + ordenSeleccionada.getId());

        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                if (ordenController.eliminarOrden(ordenSeleccionada)) {
                    mostrarAlerta("Éxito", "Orden eliminada correctamente", Alert.AlertType.INFORMATION);
                }
                cargarOrdenes();
            }
        });
    }

    @FXML
    private void marcarCompletado(){
        ordenSeleccionada = tablaOrdenes.getSelectionModel().getSelectedItem();

        if (ordenSeleccionada == null) {
            mostrarAlertaError("Seleccione una orden para marcar como completada");
            return;
        }

        if (ordenSeleccionada.getEstado() == EstadoOrden.COMPLETADO) {
            mostrarAlertaError("Esta orden ya está completada");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Desea marcar esta orden como completada?");
        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                ordenSeleccionada.marcarCompletado();
                ordenController.actualizarOrden(ordenSeleccionada);
                tablaOrdenes.refresh();
            }
        });

    }

    @FXML
    private void onVerDetalle() {

        OrdenReparacion ordenSeleccionada =
                tablaOrdenes.getSelectionModel().getSelectedItem();

        if (ordenSeleccionada == null) {
            mostrarAlertaError("Seleccione una orden para ver el detalle");
            return;
        }

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/org/demo/Views/OrdenServicioView/DetalleOrden-view.fxml"
                    )
            );

            Parent detalle = loader.load();

            DetalleOrdenController controller = loader.getController();
            controller.setOrden(ordenSeleccionada);

            StackPane contenedor =
                    (StackPane) tablaOrdenes.getScene()
                            .lookup("#contenedorCentro");

            contenedor.getChildren().setAll(detalle);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}