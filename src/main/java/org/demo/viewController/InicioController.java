package org.demo.viewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class InicioController {
    @FXML
    private Button btnClose;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;


    private double x = 0;
    private double y = 0;


    @FXML
    //Metodo de prueba para abrir el dashboard principal sin crear usuarios
    public void loginAdmin() {


        if (txtUsername.getText().isEmpty() ||
                txtPassword.getText().isEmpty()) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Ingrese usuario y contraseña");
            return;
        }

        try {

            // Cargar dashboard
            Parent root = FXMLLoader.load(
                    getClass().getResource("/org/demo/Views/Taller-view.fxml")
            );

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Sistema Taller Bicicletas");
            stage.show();

            // Cerrar ventana login actual
            Stage loginStage = (Stage) btnLogin.getScene().getWindow();
            loginStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close(){
        System.exit(0);
    }


    private void showAlert(Alert.AlertType type, String title, String content){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
