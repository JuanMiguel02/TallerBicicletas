package org.demo.viewController.VistasBicicletas;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import org.demo.controller.BicicletaController;
import org.demo.models.Bicicleta;
import org.demo.models.Cliente;
import org.demo.models.TipoBicicleta;

import java.time.Year;

import static org.demo.services.ServicioAlerta.mostrarAlerta;
import static org.demo.services.ServicioAlerta.mostrarAlertaError;

public class FormularioBicicletasController {

    private final BicicletaController bicicletaController = new BicicletaController();
    private Bicicleta bicicletaEditar;

    @FXML
    private TextField txtMarca;

    @FXML
    private ComboBox<TipoBicicleta> cmbTipoBicicleta;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtNumeroSerie;

    @FXML
    private ComboBox<Cliente> cmbCliente;

    @FXML
    private Spinner<Integer> spAnio;

    @FXML
    public void initialize() {

        // Cargar tipos de bicicleta
        cmbTipoBicicleta.getItems().setAll(TipoBicicleta.values());

        // Cargar clientes registrados
        cmbCliente.getItems().setAll(bicicletaController.obtenerClientes());

        // Configurar spinner de año
        spAnio.setValueFactory(
                new IntegerSpinnerValueFactory(1900, 2100, Year.now().getValue())
        );
    }

    private Bicicleta crearBicicleta() {

        String marca = txtMarca.getText();
        TipoBicicleta tipo = cmbTipoBicicleta.getValue();
        String color = txtColor.getText();
        String numeroSerie = txtNumeroSerie.getText();
        Cliente propietario = cmbCliente.getValue();
        Year anio = Year.of(spAnio.getValue());

        return new Bicicleta(marca, tipo, color, numeroSerie, propietario, anio);

    }

    @FXML
    private void guardarBicicleta() {

        if(!validarCampos()){
            mostrarAlertaError("Por favor rellene todos los campos");
            return;
        }

        try {

            if (bicicletaEditar == null) {

                // MODO CREAR
                Bicicleta nueva = crearBicicleta();

                if(bicicletaController.registrarBicicleta(nueva)){
                    mostrarAlerta("Éxito", "Bicicleta registrada correctamente", Alert.AlertType.INFORMATION);
                }
                else{
                    mostrarAlertaError("Esta bicicleta con número de serie: " + nueva.getNumeroSerie() + " ya existe");
                }

            } else {

                // MODO EDITAR
                bicicletaEditar.setMarca(txtMarca.getText());
                bicicletaEditar.setTipoBicicleta(cmbTipoBicicleta.getValue());
                bicicletaEditar.setColor(txtColor.getText());
                bicicletaEditar.setNumeroSerie(txtNumeroSerie.getText());
                bicicletaEditar.setPropietario(cmbCliente.getValue());
                bicicletaEditar.setAnio(Year.of(spAnio.getValue()));

                if(bicicletaController.actualizarBicicleta(bicicletaEditar)){
                    mostrarAlerta("Éxito", "Bicicleta actualizada correctamente", Alert.AlertType.INFORMATION);
                }
                limpiarCampos();
            }

        }
         catch (Exception e) {

            mostrarAlertaError("No se ha podido registrar la bicicleta");
        }
    }

    @FXML
    private void cancelar() {
        limpiarCampos();
    }

    private void limpiarCampos() {

        txtMarca.clear();
        txtColor.clear();
        txtNumeroSerie.clear();
        cmbCliente.getSelectionModel().clearSelection();
        cmbTipoBicicleta.getSelectionModel().clearSelection();
        spAnio.getValueFactory().setValue(Year.now().getValue());

        bicicletaEditar = null;
    }

    private boolean validarCampos(){
        return !txtMarca.getText().isEmpty() && !txtColor.getText().isEmpty() && !txtNumeroSerie.getText().isEmpty() && cmbCliente.getValue() != null && cmbTipoBicicleta.getValue() != null && spAnio.getValue() != null;
    }

    public void setBicicleta(Bicicleta bicicleta) {

        this.bicicletaEditar = bicicleta;

        txtMarca.setText(bicicleta.getMarca());
        txtColor.setText(bicicleta.getColor());
        txtNumeroSerie.setText(bicicleta.getNumeroSerie());
        cmbTipoBicicleta.setValue(bicicleta.getTipoBicicleta());
        cmbCliente.setValue(bicicleta.getPropietario());
        spAnio.getValueFactory().setValue(bicicleta.getAnio().getValue());
    }
}