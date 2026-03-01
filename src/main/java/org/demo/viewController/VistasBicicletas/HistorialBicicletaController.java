package org.demo.viewController.VistasBicicletas;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.demo.controller.TallerController;
import org.demo.models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class HistorialBicicletaController {

    // Labels bicicleta
    @FXML private Label lblMarca;
    @FXML private Label lblTipo;
    @FXML private Label lblColor;
    @FXML private Label lblAnio;
    @FXML private Label lblPropietario;

    // Tabla
    @FXML private TableView<OrdenReparacion> tablaServicios;
    @FXML private TableColumn<OrdenReparacion, String> colFecha;
    @FXML private TableColumn<OrdenReparacion, String> colMotivo;
    @FXML private TableColumn<OrdenReparacion, String> colEstado;
    @FXML private TableColumn<OrdenReparacion, String> colMecanico;

    private final TallerController tallerController = new TallerController();
    private Bicicleta bicicletaSeleccionada;

    @FXML
    public void initialize() {

        // Configurar columnas
        colFecha.setCellValueFactory(data -> {
            LocalDateTime fecha = data.getValue().getFechaYHora();
            String texto = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return new javafx.beans.property.SimpleStringProperty(texto);
        });

        colMotivo.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getMotivo().toString()
                )
        );

        colEstado.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getEstado().toString()
                )
        );

        colMecanico.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getMecanico().getNombreCompleto()
                )
        );
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicletaSeleccionada = bicicleta;

        cargarDatosBicicleta();
        cargarHistorial();
    }

    private void cargarDatosBicicleta() {
        lblMarca.setText(bicicletaSeleccionada.getMarca());
        lblTipo.setText(bicicletaSeleccionada.getTipoBicicleta().toString());
        lblColor.setText(bicicletaSeleccionada.getColor());
        lblAnio.setText(bicicletaSeleccionada.getAnio().toString());
        lblPropietario.setText(
                bicicletaSeleccionada.getPropietario().getNombreCompleto()
        );
    }

    private void cargarHistorial() {

        ObservableList<OrdenReparacion> historial =
                FXCollections.observableArrayList(
                        tallerController.listarOrdenes()
                                .stream()
                                .filter(o -> o.getBicicleta()
                                        .getNumeroSerie()
                                        .equals(bicicletaSeleccionada.getNumeroSerie()))
                                .collect(Collectors.toList())
                );

        tablaServicios.setItems(historial);
    }

    @FXML
    private void volver() {
        Stage stage = (Stage) tablaServicios.getScene().getWindow();
        stage.close();
    }
}