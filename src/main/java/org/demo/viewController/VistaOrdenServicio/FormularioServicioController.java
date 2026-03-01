package org.demo.viewController.VistaOrdenServicio;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.demo.controller.OrdenServicioController;
import org.demo.models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.demo.services.ServicioAlerta.mostrarAlerta;
import static org.demo.services.ServicioAlerta.mostrarAlertaError;

public class FormularioServicioController {

    private final OrdenServicioController ordenController = new OrdenServicioController();

    @FXML
    private ComboBox<Bicicleta> cmbBicicleta;

    @FXML
    private ComboBox<Mecanico> cmbMecanico;

    @FXML
    private ComboBox<MotivoReparacion> cmbMotivo;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextField txtHora;

    @FXML
    private TextField txtCosto;

    @FXML
    private TextArea txtDiagnostico;

    @FXML
    private TextArea txtTrabajos;

    @FXML
    public void initialize() {

        cmbMotivo.getItems().setAll(MotivoReparacion.values());

        cmbBicicleta.getItems().setAll(ordenController.listarBicicletas());
        cmbMecanico.getItems().setAll(ordenController.listarMecanicos());
    }

    @FXML
    private void onGuardarOrden() {

        if (!validarCampos()) {
            mostrarAlertaError("Por favor complete todos los campos");
            return;
        }

        try {

            if (ordenEditar == null) {
                // MODO CREAR
                OrdenReparacion nuevaOrden = crearOrden();

                if (ordenController.registrarOrden(nuevaOrden)) {
                    mostrarAlerta("Éxito", "Orden registrada correctamente", Alert.AlertType.INFORMATION);
                } else {
                    mostrarAlertaError("No se pudo registrar la orden");
                }

            }


            limpiarCampos();

        } catch (Exception e) {
            mostrarAlertaError("Error al guardar la orden");
        }
    }

    private OrdenReparacion crearOrden() {

        Bicicleta bicicleta = cmbBicicleta.getValue();
        Mecanico mecanico = cmbMecanico.getValue();
        MotivoReparacion motivo = cmbMotivo.getValue();

        LocalDate fecha = dpFecha.getValue();
        LocalTime hora = LocalTime.parse(txtHora.getText());
        LocalDateTime fechaYHora = LocalDateTime.of(fecha, hora);

        int costo = Integer.parseInt(txtCosto.getText());

        String diagnostico = txtDiagnostico.getText();
        String trabajos = txtTrabajos.getText();

        return new OrdenReparacion(
                bicicleta.getPropietario(),
                bicicleta,
                mecanico,
                fechaYHora,
                diagnostico,
                motivo,
                costo,
                trabajos
        );
    }


    @FXML
    private void onCancelar() {
        limpiarCampos();
    }

    private void limpiarCampos() {
        cmbBicicleta.getSelectionModel().clearSelection();
        cmbMecanico.getSelectionModel().clearSelection();
        cmbMotivo.getSelectionModel().clearSelection();
        dpFecha.setValue(null);
        txtHora.clear();
        txtCosto.clear();
        txtDiagnostico.clear();
        txtTrabajos.clear();
    }

    private boolean validarCampos() {
        return cmbBicicleta.getValue() != null
                && cmbMecanico.getValue() != null
                && cmbMotivo.getValue() != null
                && dpFecha.getValue() != null
                && !txtHora.getText().isEmpty()
                && !txtCosto.getText().isEmpty()
                && !txtDiagnostico.getText().isEmpty()
                && !txtTrabajos.getText().isEmpty();
    }

}