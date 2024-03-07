package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.bo.BoFactory;
import org.example.bo.custom.SignUpBo;
import org.example.dto.UserDto;

public class SignUpController {

    public TextField txtEmail;
    @FXML
    private CheckBox chkTerms;

    @FXML
    private PasswordField txtConfPassword;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    SignUpBo sbo = (SignUpBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.SIGN_UP);

    @FXML
    void cancelOnAction(ActionEvent event) {

    }

    @FXML
    void signUpOnAction(ActionEvent event) {
        if (!chkTerms.isSelected()){
            //Flash
            System.out.println("terms and conditions are not met");
        }
        else {
            String name = txtName.getText();
            String userName = txtUserName.getText();
            String passwordText = txtPassword.getText();
            String email = txtEmail.getText();

            boolean b = sbo.saveUser(new UserDto(name, userName, passwordText, email));
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"User Saved Successfully").show();
            }
        }
    }

}
