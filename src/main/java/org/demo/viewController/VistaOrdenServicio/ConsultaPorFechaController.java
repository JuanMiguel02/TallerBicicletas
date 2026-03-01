package org.demo.viewController.VistaOrdenServicio;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.demo.controller.OrdenServicioController;
import org.demo.models.OrdenReparacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static org.demo.services.ServicioAlerta.mostrarAlertaError;

public class ConsultaPorFechaController {

    private final OrdenServicioController ordenController = new OrdenServicioController();
    private ObservableList<OrdenReparacion> listaFiltrada;

    @FXML private TableView<OrdenReparacion> tablaOrdenes;

    @FXML private TableColumn<OrdenReparacion, String> colNumero;
    @FXML private TableColumn<OrdenReparacion, String> colCliente;
    @FXML private TableColumn<OrdenReparacion, String> colBicicleta;
    @FXML private TableColumn<OrdenReparacion, String> colMecanico;
    @FXML private TableColumn<OrdenReparacion, String> colMotivo;
    @FXML private TableColumn<OrdenReparacion, String> colFecha;
    @FXML private TableColumn<OrdenReparacion, Number> colCosto;
    @FXML private TableColumn<OrdenReparacion, String> colEstado;

    @FXML private DatePicker dpInicio;
    @FXML private DatePicker dpFin;

    @FXML
    public void initialize() {
        inicializarTabla();
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

    @FXML
    private void onBuscar() {

        LocalDate fechaInicio = dpInicio.getValue();
        LocalDate fechaFin = dpFin.getValue();

        if (fechaInicio == null || fechaFin == null) {
            mostrarAlertaError("Debe seleccionar ambas fechas");
            return;
        }

        if (fechaInicio.isAfter(fechaFin)) {
            mostrarAlertaError("La fecha inicio no puede ser mayor que la fecha fin");
            return;
        }

        List<OrdenReparacion> todasLasOrdenes = ordenController.listarOrdenes();

        List<OrdenReparacion> filtradas = todasLasOrdenes.stream()
                .filter(orden -> {
                    LocalDate fechaOrden = orden.getFechaYHora().toLocalDate();
                    return !fechaOrden.isBefore(fechaInicio)
                            && !fechaOrden.isAfter(fechaFin);
                })
                .collect(Collectors.toList());

        listaFiltrada = FXCollections.observableArrayList(filtradas);
        tablaOrdenes.setItems(listaFiltrada);
    }

    @FXML
    private void onVolver() {
        tablaOrdenes.getItems().clear();
        dpInicio.setValue(null);
        dpFin.setValue(null);
    }
}