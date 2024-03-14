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
import org.example.bo.custom.UserDashBo;
import org.example.dto.BookDto;
import org.example.dto.LogDto;
import org.example.dto.UserDto;
import org.example.model.BookTm;
import org.example.model.LogTm;

import java.util.List;

public class UserDashController {

    public TableView tblHistory;
    public TableColumn historyTid;
    public TableColumn historyBookID;
    public TableColumn historyBorrowedDate;
    public TableColumn historyRetDate;
    public TableColumn historyStatus;
    public Label txtUserName;
    public Label txtUID;
    public Label txtUName;
    public Label txtName;
    public Label txtEmail;
    public Label txtPassWord;
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

    private UserDashBo bo = (UserDashBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.USERDASH);
    @FXML
    void searchOnAction(ActionEvent event) {

    }

    public void initialize(){
     setCellValueFactory();
     loadBooks();
    }

    private void loadBooks() {
        ObservableList<BookTm> oblist = FXCollections.observableArrayList();
        List<BookDto> list = bo.getAllBooks();
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

    public void setCellValueFactory(){
        bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        bookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        bookAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        bookBranch.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        bookOptions.setCellValueFactory(new PropertyValueFactory<>("button"));

        historyTid.setCellValueFactory(new PropertyValueFactory<>("Tid"));
        historyBookID.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        historyBorrowedDate.setCellValueFactory(new PropertyValueFactory<>("borrwedDate"));
        historyRetDate.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));
        historyStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    public void loadLogs(){
        ObservableList<LogTm> oblist = FXCollections.observableArrayList();
        List<LogDto> list = bo.getAllLogsForThisUser(txtUID.getText());
        for (LogDto d : list){
            oblist.add(
                    new LogTm(
                            d.getTid(),
                            d.getBookName(),
                            d.getBorrwedDate(),
                            d.getReturnedDate(),
                            d.isStatus()
                    )
            );
            tblHistory.setItems(oblist);
            tblHistory.refresh();
        }
    }
    public void setUp(String userName){
        UserDto user = bo.getUser(userName);
        txtUID.setText(user.getUserId());
        txtUserName.setText(user.getUserName());
        txtName.setText(user.getName());
        txtPassWord.setText(user.getPassword());
        txtEmail.setText(user.getEmail());
        txtUName.setText(user.getUserName());
        loadLogs();
    }
}
