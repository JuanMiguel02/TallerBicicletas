package org.demo.viewController.VistasClientes;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.demo.controller.ClienteController;
import org.demo.models.Cliente;
import static org.demo.services.ServicioAlerta.mostrarAlerta;
import static org.demo.services.ServicioAlerta.mostrarAlertaError;

public class FormularioClienteController {

    private final ClienteController clienteController=new ClienteController();

    private Cliente clienteEditar;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtDireccion;

    @FXML
    private void  guardarCliente (){

        if(!validarCampos()){
            mostrarAlertaError("Por favor rellene todos los campos");
            return;
        }

        try{
            if(clienteEditar==null){
                Cliente nuevo=crearCliente();
                if(clienteController.registrarCliente(nuevo)){
                    mostrarAlerta("Exito", "Cliente registrado correctamente", Alert.AlertType.INFORMATION);

                }else{
                    mostrarAlertaError("Este cliente con documento: " + nuevo.getDocumento() + " ya existe");
                }

            }else {
                clienteEditar.setNombreCompleto(txtNombre.getText());
                clienteEditar.setTelefono(txtTelefono.getText());
                clienteEditar.setDocumento(txtDocumento.getText());
                clienteEditar.setDireccion(txtDireccion.getText());

                if(clienteController.actualizarCliente(clienteEditar)){
                    mostrarAlerta("Exito", "Cliente registrado correctamente", Alert.AlertType.INFORMATION);
                }
            }
            limpiarCampos();
        } catch (Exception e) {
            mostrarAlertaError("No se ha podido registrar el cliente");
        }
    }

    private Cliente crearCliente(){
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String documento = txtDocumento.getText();
        String direccion = txtDireccion.getText();

        return new Cliente(nombre, telefono, documento, direccion);
    }

    @FXML
    private void cancelar(){
        limpiarCampos();
    }

    private void limpiarCampos(){
        txtDireccion.clear();
        txtDocumento.clear();
        txtNombre.clear();
        txtTelefono.clear();
        clienteEditar=null;
    }

    private boolean validarCampos(){
        return !txtNombre.getText().isEmpty() && !txtTelefono.getText().isEmpty() && !txtDocumento.getText().isEmpty() && !txtDireccion.getText().isEmpty();
    }

    public void setCliente(Cliente cliente){
        this.clienteEditar=cliente;

        txtTelefono.setText(cliente.getTelefono());
        txtDireccion.setText(cliente.getTelefono());
        txtNombre.setText(cliente.getNombreCompleto());
        txtDocumento.setText(cliente.getDocumento());
    }
}
