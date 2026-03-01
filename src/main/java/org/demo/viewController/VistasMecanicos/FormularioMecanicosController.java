package org.demo.viewController.VistasMecanicos;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.demo.controller.MecanicoController;
import org.demo.models.EspecialidadMecanico;
import org.demo.models.Mecanico;

import static org.demo.services.ServicioAlerta.mostrarAlerta;
import static org.demo.services.ServicioAlerta.mostrarAlertaError;

public class FormularioMecanicosController {

    private final MecanicoController mecanicoController = new MecanicoController();
    private Mecanico mecanicoEditar;

    @FXML
    private ComboBox<EspecialidadMecanico> cmbEspecialidad;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtSueldo;

    @FXML
    private TextField txtTelefono;

    @FXML
    public void initialize() {

        cmbEspecialidad.getItems().setAll(EspecialidadMecanico.values());

    }

    @FXML
    private void guardarMecanico(){

        if(validarCampos()){
            mostrarAlertaError("Por favor rellene todos los campos");
            return;
        }

        try {
            if (mecanicoEditar == null) {
                // MODO CREAR
                Mecanico mecanicoNuevo = crearMecanico();

                if(mecanicoController.registrarMecanico(mecanicoNuevo)){
                    mostrarAlerta("Éxito", "Mecánico registrado", Alert.AlertType.INFORMATION);

                }else{
                    mostrarAlertaError("Este mecánico con documento: " + mecanicoNuevo.getDocumento() + " ya existe");
                }

            } else {
                // MODO EDITAR
                mecanicoEditar.setNombreCompleto(txtNombre.getText());
                mecanicoEditar.setDireccion(txtDireccion.getText());
                mecanicoEditar.setDocumento(txtDocumento.getText());
                mecanicoEditar.setTelefono(txtTelefono.getText());
                mecanicoEditar.setSueldo(Integer.parseInt(txtSueldo.getText()));
                mecanicoEditar.setEspecialidad(cmbEspecialidad.getValue());

                if(mecanicoController.actualizarMecanico(mecanicoEditar)){
                    mostrarAlerta("Éxito", "Mecánico actualizado", Alert.AlertType.INFORMATION);
                }

            }
            limpiarCampos();
        }catch(Exception e){
            mostrarAlertaError("No se ha podido registrar al mecánico");
        }

    }

    private Mecanico crearMecanico() {
        String nombreCompleto = txtNombre.getText();
        String direccion = txtDireccion.getText();
        String documento = txtDocumento.getText();
        String telefono = txtTelefono.getText();
        int sueldo = Integer.parseInt(txtSueldo.getText());
        EspecialidadMecanico especialidadMecanico =  cmbEspecialidad.getSelectionModel().getSelectedItem();

        return new Mecanico(nombreCompleto, telefono, documento, direccion, sueldo, especialidadMecanico);
    }


    @FXML
    private void cancelar(){
        limpiarCampos();
    }

    private void limpiarCampos(){  
        txtNombre.clear();
        txtDireccion.clear();
        txtDocumento.clear();
        txtSueldo.clear();
        txtTelefono.clear();
        cmbEspecialidad.getSelectionModel().clearSelection();
    }

    private boolean validarCampos(){
        return !txtNombre.getText().isEmpty() && !txtDireccion.getText().isEmpty() && !txtDocumento.getText().isEmpty()  && !txtSueldo.getText().isEmpty() &&  !txtTelefono.getText().isEmpty()
                && cmbEspecialidad.getValue() != null;
    }

    public void setMecanico(Mecanico mecanico){
        this.mecanicoEditar = mecanico;

        txtNombre.setText(mecanico.getNombreCompleto());
        txtDireccion.setText(mecanico.getDireccion());
        txtDocumento.setText(mecanico.getDocumento());
        txtTelefono.setText(mecanico.getTelefono());
        txtSueldo.setText(String.valueOf(mecanico.getSueldo()));
        cmbEspecialidad.setValue(mecanico.getEspecialidad());
    }
}
