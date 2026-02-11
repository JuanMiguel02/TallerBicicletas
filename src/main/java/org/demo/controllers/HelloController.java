package org.demo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HelloController {
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


    public void loginAdmin() {
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
