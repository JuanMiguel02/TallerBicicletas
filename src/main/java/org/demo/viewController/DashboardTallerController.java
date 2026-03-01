package org.demo.viewController;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.demo.controller.TallerController;
import org.demo.models.Bicicleta;
import org.demo.models.Cliente;
import org.demo.models.EstadoOrden;
import org.demo.models.OrdenReparacion;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DashboardTallerController {
    @FXML
    private BorderPane root;

    @FXML
    private StackPane contenedorCentro;

    @FXML
    private AnchorPane vistaInicio;

    @FXML
    private AreaChart<String, Number> graficaServicios;

    @FXML
    private Label lblOrdenesActivas;

    @FXML
    private Label lblTotalClientes;

    @FXML
    private Label lblTotalBicicletas;

    private final TallerController tallerController= new TallerController();

    private LocalDate fecha;


    @FXML
    public void initialize(){
        contenedorCentro.getChildren().setAll(vistaInicio);

        //cargar las que ya existen
        cargarEstadisticas();
        cargarGraficaServicios();

        //actualizar cuando se agrega algo nuevo
        tallerController.listarClientes().addListener(
                (ListChangeListener<Cliente>) change -> {
                    lblTotalClientes.setText(
                            String.valueOf(
                                    tallerController.listarClientes().size()
                            )
                    );
                }
        );

        tallerController.listarBicicletas().addListener(
                (ListChangeListener <Bicicleta>) change -> lblTotalBicicletas.setText(
                        String.valueOf(
                                tallerController.listarBicicletas().size()
                        )
                )
        );

        tallerController.listarOrdenes().addListener(
                (ListChangeListener< OrdenReparacion>) change -> {
                    actualizarOrdenesActivas();
                    cargarGraficaServicios();
                }
        );

    }

    @FXML
    private void mostrarInicio() {
        contenedorCentro.getChildren().setAll(vistaInicio);
    }

    @FXML
    private void OnCliente() {
        cargarVista("/org/demo/Views/ClienteView/TablaClientes-view.fxml");
    }

    @FXML
    private void OnBicicleta() {
        cargarVista("/org/demo/Views/BicicletaView/TablaBicicleta-view.fxml");
    }

    @FXML
    private void OnMecanico() {
        cargarVista("/org/demo/Views/MecanicoView/TablaMecanico-view.fxml");
    }

    @FXML
    private void OnOrdenServicio() {
        cargarVista("/org/demo/Views/OrdenServicioView/TablaOrdenServicio-view.fxml");
    }

    @FXML
    private void OnConsulta() {
        cargarVista("/org/demo/Views/OrdenServicioView/ConsultaPorFecha-view.fxml");
    }

    @FXML
    private void volverLogin() {
        try {

            Parent login = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(
                            "/org/demo/inicio.fxml"
                    ))
            );

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(login));
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void cerrar() {
        System.exit(0);
    }

    public void minimizar() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);

    }


    private void cargarVista(String rutaFXML) {
        try {
            Parent vista = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource(rutaFXML))
            );
            contenedorCentro.getChildren().clear();
            contenedorCentro.getChildren().add(vista);
        }
         catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarEstadisticas() {

        int totalClientes = tallerController.listarClientes().size();
        int totalBicicletas = tallerController.listarBicicletas().size();

        int ordenesActivas = (int) tallerController.listarOrdenes()
                .stream()
                .filter(o -> o.getEstado() == EstadoOrden.EN_CURSO)
                .count();

        lblTotalClientes.setText(String.valueOf(totalClientes));
        lblTotalBicicletas.setText(String.valueOf(totalBicicletas));
        lblOrdenesActivas.setText(String.valueOf(ordenesActivas));
    }

    private void actualizarOrdenesActivas() {
        long activas = tallerController.listarOrdenes()
                .stream()
                .filter(o -> o.getEstado() == EstadoOrden.EN_CURSO)
                .count();

        lblOrdenesActivas.setText(String.valueOf(activas));
    }


    private void cargarGraficaServicios() {

        graficaServicios.getData().clear();

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Servicios por mes");

        Map<Month, Long> serviciosPorMes = tallerController.listarOrdenes()
                .stream()
                .filter(o -> o.getEstado() == EstadoOrden.COMPLETADO) // solo servicios hechos
                .collect(Collectors.groupingBy(
                        o -> o.getFechaYHora().getMonth(),
                        Collectors.counting()
                ));

        for (Month mes : Month.values()) {
            long cantidad = serviciosPorMes.getOrDefault(mes, 0L);
            serie.getData().add(
                    new XYChart.Data<>(mes.getDisplayName(TextStyle.SHORT, new Locale("es")), cantidad)
            );
        }

        graficaServicios.getData().add(serie);
    }

}
