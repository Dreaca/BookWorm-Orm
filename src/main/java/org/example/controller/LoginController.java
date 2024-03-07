package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.LoginBo;

import java.io.IOException;

public class LoginController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void loginOnAction(ActionEvent event) {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();
        if (bo.validate(userName,password)) {
            Stage window = (Stage) txtUsername.getScene().getWindow();
            window.close();
            new Alert(Alert.AlertType.CONFIRMATION,"Login success").show();
        }
    }
    LoginBo bo = (LoginBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.LOGIN);
    @FXML
    void signUpOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/signUpForm.fxml"));
        Object load = loader.load();
        Scene scene = new Scene((Parent) load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void initialize(){
        /*boolean b = bo.checkAdmin();
        if (!b){
            Alert alert = new Alert(Alert.AlertType.WARNING, "NO ADMIN DETECTED", new ButtonType("New Admin"));
            alert.showAndWait().ifPresent(response ->{
                System.out.println("working");
            });
        }*/
    }

}
