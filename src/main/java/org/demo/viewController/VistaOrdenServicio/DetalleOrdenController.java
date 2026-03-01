package org.demo.viewController.VistaOrdenServicio;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.demo.models.OrdenReparacion;

import java.time.format.DateTimeFormatter;

public class DetalleOrdenController {

    @FXML
    private Label lblClienteId;

    @FXML
    private Label lblClienteNombre;

    @FXML
    private Label lblColor;

    @FXML
    private Label lblCosto;

    @FXML
    private Label lblEstado;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblHora;

    @FXML
    private Label lblMarca;

    @FXML
    private Label lblMecanico;

    @FXML
    private Label lblMotivo;

    @FXML
    private Label lblNumero;

    @FXML
    private Label lblSerial;

    @FXML
    private Label lblTelefono;

    @FXML
    private Label lblTipo;

    @FXML
    private TextArea txtDiagnostico;

    @FXML
    private TextArea txtTrabajos;

    public void setOrden(OrdenReparacion orden) {

        DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");

        lblNumero.setText(orden.getId().toString());
        lblEstado.setText(orden.getEstado().toString());
        lblCosto.setText(String.valueOf(orden.getCosto()));
        lblMotivo.setText(orden.getMotivo().toString());

        lblFecha.setText(orden.getFechaYHora().format(fechaFormatter));
        lblHora.setText(orden.getFechaYHora().format(horaFormatter));

        lblMecanico.setText(orden.getMecanico().getNombreCompleto());

        lblClienteNombre.setText(orden.getCliente().getNombreCompleto());
        lblClienteId.setText(orden.getCliente().getDocumento());
        lblTelefono.setText(orden.getCliente().getTelefono());

        lblMarca.setText(orden.getBicicleta().getMarca());
        lblColor.setText(orden.getBicicleta().getColor());
        lblTipo.setText(orden.getBicicleta().getTipoBicicleta().toString());
        lblSerial.setText(orden.getBicicleta().getNumeroSerie());

        txtDiagnostico.setText(orden.getDiagnostico());
        txtTrabajos.setText(orden.getDescripcionTrabajo());
    }

}
