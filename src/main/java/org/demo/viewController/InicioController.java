package org.demo.viewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.demo.models.Taller;

import java.util.Objects;

import static org.demo.services.ServicioAlerta.mostrarAlertaError;

public class InicioController {

    private final Taller taller = Taller.getInstancia();

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;


    private double x = 0;
    private double y = 0;


    @FXML
    //Metodo de prueba para abrir el dashboard principal sin crear usuarios
    public void loginAdmin() {


        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {

            mostrarAlertaError("Rellene todos lo campos");
            return;
        }

        try {

            if(txtUsername.getText().equals(taller.getNombreUsuario()) && txtPassword.getText().equals(taller.getContrasenia())){
                // Cargar dashboard
                Parent root = FXMLLoader.load(
                        Objects.requireNonNull(getClass().getResource("/org/demo/Views/Taller-view.fxml"))
                );
                Stage stage = new Stage();
                Scene scene = new Scene(root, 1280, 720);

                root.setOnMousePressed(event -> {
                    x = event.getSceneX();
                    y = event.getSceneY();

                });

                root.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(0.8);
                });

                root.setOnMouseReleased(event -> stage.setOpacity(1));

                stage.initStyle(StageStyle.TRANSPARENT);

                // Cerrar ventana login actual
                stage.setScene(scene);
                stage.show();
            }else{
                mostrarAlertaError("Usuario o contraseña inválidos");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        System.exit(0);
    }

}
