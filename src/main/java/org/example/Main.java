package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.config.FactoryConfiguration;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/login.fxml"));
        Object load = loader.load();
        Scene scene = new Scene((Parent) load);
        stage.setScene(scene);
        stage.show();
    }
}