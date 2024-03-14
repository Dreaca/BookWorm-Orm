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
import org.example.util.RegexUtil;

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

            if (!RegexUtil.matchesRegex(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid email address").show();
                return;
            }

            if (!RegexUtil.matchesRegex(userName, "^[a-zA-Z0-9_]{4,}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid username").show();
                return;
            }

            if (!RegexUtil.matchesRegex(passwordText, "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid password").show();
                return;
            }
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
