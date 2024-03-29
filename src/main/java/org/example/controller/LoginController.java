package org.example.controller;

import com.jfoenix.controls.JFXToggleNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.LoginBo;
import org.example.util.RegexUtil;

import java.io.IOException;

public class LoginController {

    public JFXToggleNode toggleButton;
    public TextField txtShowPassword;
    public ImageView toggleImage;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        if (!RegexUtil.matchesRegex(userName, "^[a-zA-Z0-9_]{4,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid username").show();
            return;
        }

        if (!RegexUtil.matchesRegex(password, "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid password").show();
            return;
        }

        if (bo.validate(userName,password)) {
            Stage window = (Stage) txtUsername.getScene().getWindow();
            window.close();
            if (bo.checkUser(userName).equals("ADMIN")){
                loadAdminDash(userName);
            }
            else{
                loadUserDash(userName);
            }
        }
    }


    private void loadUserDash(String userName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/userDash.fxml"));
        Object load = loader.load();
        UserDashController controller = loader.getController();
        controller.setUp(userName);
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) load);
        stage.setScene(scene);
        stage.show();
    }

    private void loadAdminDash(String userName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Admin.fxml"));
        Object load = loader.load();
        DashboardController controller = loader.getController();
        controller.setUp(userName);
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) load);
        stage.setScene(scene);
        stage.show();
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
        boolean b = bo.checkAdmin();
        if (!b){
            Alert alert = new Alert(Alert.AlertType.WARNING, "NO ADMIN DETECTED", new ButtonType("New Admin"));
            alert.showAndWait().ifPresent(response ->{
                try {
                    openAdminSignUp();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void openAdminSignUp() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/AdminSignUp.fxml"));
        Object load = loader.load();
        Scene scene = new Scene((Parent) load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void showPassword(MouseEvent event) {
        String text = txtPassword.getText();
        txtShowPassword.setText(text);
        txtShowPassword.setVisible(true);
        toggleImage.setImage(new Image("assets/hide.png"));

    }

    public void hidePassword(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(false);
        toggleImage.setImage(new Image("assets/view.png"));
    }
}
