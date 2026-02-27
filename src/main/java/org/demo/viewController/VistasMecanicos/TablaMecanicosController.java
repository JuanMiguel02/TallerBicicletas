package org.demo.viewController.VistasMecanicos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import org.demo.controller.MecanicoController;
import org.demo.models.EspecialidadMecanico;
import org.demo.models.Mecanico;

import java.util.Objects;

import static org.demo.services.ServicioAlerta.mostrarAlerta;
import static org.demo.services.ServicioAlerta.mostrarAlertaError;

public class TablaMecanicosController {

    private final MecanicoController mecanicoController = new MecanicoController();
    private ObservableList<Mecanico> listaMecanicos;
    private Mecanico mecanicoSeleccionado;

    @FXML
    private TableColumn<Mecanico, String> colCodigoMecanico;

    @FXML
    private TableColumn<Mecanico, String> colDireccion;

    @FXML
    private TableColumn<Mecanico, String> colDocumento;

    @FXML
    private TableColumn<Mecanico, String> colEspecialidad;

    @FXML
    private TableColumn<Mecanico, String> colNombreCompleto;

    @FXML
    private TableColumn<Mecanico, Number> colSueldo;

    @FXML
    private TableColumn<Mecanico, String> colTelefono;

    @FXML
    private TableView<Mecanico> tablaMecanicos;

    @FXML
    public void initialize() {

        inicializarTabla();
        cargarMecanicos();
    }

    private void inicializarTabla(){
        colCodigoMecanico.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCodigoInterno()));
        colNombreCompleto.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombreCompleto()));
        colDocumento.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDocumento()));
        colTelefono.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTelefono()));
        colDireccion.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDireccion()));
        colSueldo.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getSueldo()));
        colEspecialidad.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEspecialidad().toString()));
    }

    private void cargarMecanicos(){
        listaMecanicos = FXCollections.observableArrayList(mecanicoController.listarMecanicos());
        tablaMecanicos.setItems(listaMecanicos);
    }

    @FXML
    private void onNuevoMecanico() {
        try {

            // Cargar formulario
            Parent formulario = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(
                            "/org/demo/Views/MecanicoView/FormularioMecanico-view.fxml"
                    ))
            );

            StackPane contenedor =
                    (StackPane) tablaMecanicos.getScene()
                            .lookup("#contenedorCentro");

            // Reemplazar vista
            contenedor.getChildren().setAll(formulario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onEditarMecanico() {

        mecanicoSeleccionado = tablaMecanicos.getSelectionModel().getSelectedItem();

        if(mecanicoSeleccionado == null){
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

            FormularioMecanicosController controller = loader.getController();
            controller.setMecanico(mecanicoSeleccionado);

            StackPane contenedor =
                    (StackPane) tablaMecanicos.getScene()
                            .lookup("#contenedorCentro");

            contenedor.getChildren().setAll(formulario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onEliminarMecanico() {

        mecanicoSeleccionado = tablaMecanicos.getSelectionModel().getSelectedItem();

        if(mecanicoSeleccionado == null){
            mostrarAlertaError("Por favor seleccione un cliente para eliminar");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro que desea eliminar este mecánico?");
        confirmacion.setContentText("Mecánico " + mecanicoSeleccionado.getNombreCompleto() + " - " + mecanicoSeleccionado
                .getDocumento());

        confirmacion.showAndWait().ifPresent(respuesta ->{
            if(respuesta == ButtonType.OK){
               mecanicoController.eliminarMecanico(mecanicoSeleccionado);
               cargarMecanicos();
                mostrarAlerta("Éxito", "Mecánico Eliminado Éxitosamente", Alert.AlertType.INFORMATION  );
            }
        });

    }
}
