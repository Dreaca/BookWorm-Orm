package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookFormController {

    @FXML
    private TableColumn<?, ?> bookAuthor;

    @FXML
    private TableColumn<?, ?> bookAvailability;

    @FXML
    private TableColumn<?, ?> bookBranch;

    @FXML
    private TableColumn<?, ?> bookGenre;

    @FXML
    private TableColumn<?, ?> bookId;

    @FXML
    private TableColumn<?, ?> bookOptions;

    @FXML
    private TableColumn<?, ?> bookTitle;

    @FXML
    private TableView<?> booktbl;

    @FXML
    private Label txtBranchId;

    @FXML
    private Label txtBranchName;

    @FXML
    void addNewBookOnAction(ActionEvent event) {

    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }
    public void setBranchNameId(String branchId,String branchName){
        txtBranchId.setText(branchId);
        txtBranchName.setText(branchName);
    }
    public void initialize(){
        setCellValueFactory();
    }
    public void setCellValueFactory(){
        bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        bookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        bookAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        bookBranch.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        bookOptions.setCellValueFactory(new PropertyValueFactory<>("button"));
    }
    public void loadBooks(){

    }

}
