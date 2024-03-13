package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    //BranchTable
    public TableColumn branchesColBranchId;
    public TableColumn branchesColLocation;
    public TableColumn branchesColNoOfBooks;
    public TableColumn branchesColBM;
    public TableColumn branchesMod;
    public TableColumn branchesColBooks;

    //UserTable
    public TableColumn UserColUID;
    public TableColumn UserColUname;
    public TableColumn userColName;
    public TableColumn userColEmail;
    public TableColumn userColMod;
    public TableColumn userColDet;

    //LogBook
    public TableColumn LogTID;
    public TableColumn logColBookName;
    public TableColumn logColUserName;
    public TableColumn logBorrowDate;
    public TableColumn logColRetDate;
    public TableColumn logColStatus;
    public TableColumn logColOptions;

    public void addNewBranchOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddBranch.fxml"));
        Object load = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) load);
        stage.setScene(scene);
        stage.show();
    }
}
