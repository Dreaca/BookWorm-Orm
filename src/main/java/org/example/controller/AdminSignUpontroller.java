package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.SignUpBo;
import org.example.dto.UserDto;

public class AdminSignUpontroller {

    @FXML
    private CheckBox chkTerms;

    @FXML
    private PasswordField txtConfPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    private SignUpBo bo = (SignUpBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.SIGN_UP);

    @FXML
    void cancelOnAction(ActionEvent event) {
       Stage stage = (Stage) txtEmail.getScene().getWindow();
       stage.close();
    }

    @FXML
    void signUpOnAction(ActionEvent event) {
        if (!chkTerms.isSelected()){
            System.out.println("Terms and conditions are not met");
        }
        else {
            String name = txtName.getText();
            String userName = txtUserName.getText();
            String passwordText = txtPassword.getText();
            String email = txtEmail.getText();
            if (!txtConfPassword.getText().equals(passwordText)){
                txtConfPassword.setStyle("-fx-border-color: red");
                return;
            }
            boolean b = bo.saveAdmin(new UserDto(name, userName, passwordText, email));
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"User Saved Successfully").show();
            }
        }
    }

}
