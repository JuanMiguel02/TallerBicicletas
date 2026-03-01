package org.demo.viewController.VistasBicicletas;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.demo.controller.BicicletaController;
import org.demo.models.Bicicleta;
import org.demo.models.Cliente;
import org.demo.models.TipoBicicleta;

import static org.demo.services.ServicioAlerta.mostrarAlerta;
import static org.demo.services.ServicioAlerta.mostrarAlertaError;

public class FormularioBicicletasController {

    private final BicicletaController bicicletaController= new BicicletaController();
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
    public void initialize() {
        cmbTipoBicicleta.getItems().setAll(TipoBicicleta.values());

        cmbCliente.getItems().setAll(bicicletaController.obtenerClientes());
    }

    private Bicicleta crearBicicleta(){
        String marca = txtMarca.getText();
        TipoBicicleta bicicleta=cmbTipoBicicleta.getSelectionModel().getSelectedItem();
        String color = txtColor.getText();
        String numeroSerie = txtNumeroSerie.getText();
        Cliente propietario = cmbCliente.getSelectionModel().getSelectedItem();

        return new Bicicleta(marca, tipo, color, numeroSerie, propietario);
    }

    @FXML
    private void guardarBicicleta(){
        try {
            if (bicicletaEditar == null) {
                // MODO CREAR
                Bicicleta nueva = crearBicicleta();
                bicicletaController.registrarBicicleta(nueva);
                mostrarAlerta("Éxito", "BicicletaRegistrada", Alert.AlertType.INFORMATION);

            } else {
                // MODO EDITAR
                bicicletaEditar.setMarca(txtMarca.getText());
                bicicletaEditar.setTipoBicicleta(cmbTipoBicicleta.getValue());
                bicicletaEditar.setColor(txtColor.getText());
                bicicletaEditar.setNumeroSerie(txtNumeroSerie.getText());
                bicicletaEditar.setPropietario(cmbCliente.getValue());

                bicicletaController.actualizarBicicleta(bicicletaEditar);
                mostrarAlerta("Éxito", "Bicicleta actualizada", Alert.AlertType.INFORMATION);
            }
        }catch(Exception e){
            mostrarAlertaError("No se ha podido registrar la bicicleta");
        }

    }

    @FXML
    private void cancelar(){
        limpiarCampos_();
    }
    private void limpiarCampos_(){
        txtMarca.clear();
        txtColor.clear();
        txtNumeroSerie.clear();
        cmbCliente.getSelectionModel().clearSelection();
        cmbTipoBicicleta.getSelectionModel().clearSelection();

        bicicletaEditar=null;
    }

    public void setBicicleta(Bicicleta bicicleta){
        txtMarca.setText(bicicleta.getMarca());
        txtColor.setText(bicicleta.getColor());
        txtNumeroSerie.setText(bicicleta.getNumeroSerie());
        cmbTipoBicicleta.setValue(bicicleta.getTipoBicicleta());
        cmbCliente.setValue(bicicleta.getPropietario());
    }
}
