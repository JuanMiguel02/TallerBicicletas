package org.demo.viewController.VistasClientes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import org.demo.controller.ClienteController;
import org.demo.models.Cliente;
import org.demo.models.Mecanico;
import org.demo.viewController.VistasMecanicos.FormularioMecanicosController;

import java.util.Objects;

import static org.demo.services.ServicioAlerta.mostrarAlerta;
import static org.demo.services.ServicioAlerta.mostrarAlertaError;

public class TablaClientesController {

    private final ClienteController clienteController = new ClienteController();
    private ObservableList<Cliente> listaClientes;
    private Cliente clienteSeleccionado;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente,String> colIdentificacion;

    @FXML
    private TableColumn<Cliente, String> colTelefono;

    @FXML
    private TableColumn<Cliente, String> colDireccion;

    @FXML
    private TableColumn<Cliente, Number> colId;

    @FXML
    public void initialize() {

        inicializarTabla();
        cargarClientes();
    }

    private void inicializarTabla(){
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombreCompleto()));
        colIdentificacion.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDocumento()));
        colTelefono.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTelefono()));
        colDireccion.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDireccion()));
        colId.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getId()));
    }

    private void cargarClientes(){
        listaClientes = FXCollections.observableArrayList(clienteController.listarClientes());
        tablaClientes.setItems(listaClientes);
    }


    @FXML
    private void onNuevoCliente() {
        try {

            // Cargar formulario
            Parent formulario = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(
                            "/org/demo/Views/ClienteView/FormularioCliente-View.fxml"
                    ))
            );


            StackPane contenedor =
                    (StackPane) tablaClientes.getScene()
                            .lookup("#contenedorCentro");

            // Reemplazar vista
            contenedor.getChildren().setAll(formulario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onEditarCliente() {
        clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if(clienteSeleccionado == null){
            mostrarAlertaError("Por favor seleccione un cliente para actualizar");
            return;
        }
        try{
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/org/demo/Views/MecanicoView/FormularioMecanico-view.fxml"
                    )
            );

            Parent formulario = loader.load();

            FormularioClienteController controller = loader.getController();
            controller.setCliente(clienteSeleccionado);

            StackPane contenedor =
                    (StackPane) tablaClientes.getScene()
                            .lookup("#contenedorCentro");

            contenedor.getChildren().setAll(formulario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onEliminarCliente() {
       clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if(clienteSeleccionado == null){
            mostrarAlertaError("Por favor seleccione un cliente para eliminar");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro que desea eliminar este cliente?");
        confirmacion.setContentText("Cliente " +clienteSeleccionado.getNombreCompleto() + " - " +clienteSeleccionado
                .getDocumento());

        confirmacion.showAndWait().ifPresent(respuesta ->{
            if(respuesta == ButtonType.OK){
                if( clienteController.eliminarCliente(clienteSeleccionado)){
                    mostrarAlerta("Éxito", "Cliente eliminado éxitosamente", Alert.AlertType.INFORMATION);
                    cargarClientes();
                }
            }
        });
    }
}

