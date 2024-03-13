package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.bo.BoFactory;
import org.example.bo.custom.AdminDashBo;
import org.example.dto.BookDto;
import org.example.model.BookTm;

import java.util.List;

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
    private TableView<BookTm> booktbl;

    @FXML
    private Label txtBranchId;

    @FXML
    private Label txtBranchName;

    private AdminDashBo bo = (AdminDashBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ADMIN);

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
        loadBooks();
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
        ObservableList<BookTm> oblist = FXCollections.observableArrayList();
        List<BookDto> list = bo.getAllBooksofThisBranch(txtBranchId.getText());
        for(BookDto dto : list){
            oblist.add(
                    new BookTm(
                            dto.getBookId(),
                            dto.getTitle(),
                            dto.getAuthor(),
                            dto.getGenre(),
                            dto.isAvailability(),
                            dto.getBranchName(),
                            new JFXButton()
                    )
            );
        }
        booktbl.setItems(oblist);
        booktbl.refresh();

    }

}
