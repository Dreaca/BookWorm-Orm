package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.UserDashBo;
import org.example.dto.BookDto;
import org.example.dto.LogDto;
import org.example.dto.UserDto;
import org.example.model.BookTm;
import org.example.model.LogTm;

import java.io.IOException;
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
    public TextField changeUName;
    public TextField changeName;
    public TextField changeEmail;
    public TextField changePass;
    public JFXButton editDone;
    public JFXButton btnProfCancel;
    public AnchorPane root;
    public TextField searchTxt;
    public TableColumn bookCount;
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
        String search = searchTxt.getText();
        BookDto dto = bo.searchBookByName(search);
        ObservableList<BookTm> oblist = FXCollections.observableArrayList();
            oblist.add(
                    new BookTm(
                            dto.getBookId(),
                            dto.getTitle(),
                            dto.getAuthor(),
                            dto.getGenre(),
                            dto.isAvailability(),
                            dto.getBookCount(),
                            dto.getBranchName(),
                            loadJFXButton()
                    ));

        for (int i = 0; i < oblist.size(); i++) {
            JFXButton bt =   oblist.get(i).getButton();
            if (oblist.get(i).getAvailability().startsWith("borrowed")){
                bt.setText("borrowed");
                bt.setDisable(true);
            }
            int finalI = i;
            bt.setOnAction(actionEvent -> {
                boolean b = bo.addLog(oblist.get(finalI).getBookId(), txtUName.getText());
                if (b){ bt.setStyle("-fx-background-color: green");
                    bt.setText("borrowed");
                    bt.setDisable(true);
                }
                loadLogs();
            });
        }
        booktbl.setItems(oblist);
        booktbl.refresh();
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
                        dto.getBookCount(),
                        dto.getBranchName(),
                        loadJFXButton()
                    )
            );
        }
        for (int i = 0; i < oblist.size(); i++) {
            JFXButton bt =   oblist.get(i).getButton();
            int finalI = i;
            int finalI1 = i;
            if (oblist.get(finalI1).getAvailability().startsWith("borrowed")){
                bt.setText("borrowed");
                bt.setDisable(true);
            }
            bt.setOnAction(actionEvent -> {
                boolean b = bo.addLog(oblist.get(finalI).getBookId(), txtUName.getText());
                if (b){ bt.setStyle("-fx-background-color: green");
                bt.setText("borrowed");
                bt.setDisable(true);
                }
                loadLogs();
            });
        }
        booktbl.setItems(oblist);
        booktbl.refresh();

    }

    private JFXButton loadJFXButton() {
            return new JFXButton("burrow");
    }

    public void setCellValueFactory(){
        bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        bookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        bookAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        bookCount.setCellValueFactory(new PropertyValueFactory<>("bookCount"));
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

    public void updateUser(ActionEvent actionEvent) {

        String changeUNameText = changeUName.getText();
        String changeNameText = changeName.getText();
        String changeEmailText = changeEmail.getText();
        String changePassText = changePass.getText();

        if (!changeUNameText.matches("^[a-zA-Z0-9_]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid username").show();
            return;
        }

        if (!changeNameText.matches("^[a-zA-Z\\s.'-]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            return;
        }

        if (!changeEmailText.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid email address").show();
            return;
        }

        if (!changePassText.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid password").show();
            return;
        }


        bo.updateUser(new UserDto(txtUID.getText(),
                changeUName.getText(),
                changeName.getText(),
                changeEmail.getText(),
                changePass.getText()
        ));
        setUp(changeUName.getText());
        changeEmail.setVisible(false);
        changeName.setVisible(false);
        changeUName.setVisible(false);
        changePass.setVisible(false);
        btnProfCancel.setVisible(false);
        editDone.setVisible(false);

    }

    public void editOnAction(ActionEvent actionEvent) {
        changeEmail.setVisible(true);
        changeName.setVisible(true);
        changeUName.setVisible(true);
        changePass.setVisible(true);
        btnProfCancel.setVisible(true);
        editDone.setVisible(true);

        changeEmail.setText(txtEmail.getText());
        changeName.setText(txtName.getText());
        changeUName.setText(txtUName.getText());
        changePass.setText(txtPassWord.getText());
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/login.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        Object load = loader.load();
        Scene scene = new Scene((Parent) load);
        stage.setScene(scene);
        stage.show();
    }

    public void cancelUpdate(ActionEvent actionEvent) {
        changeEmail.setVisible(false);
        changeName.setVisible(false);
        changeUName.setVisible(false);
        changePass.setVisible(false);
        btnProfCancel.setVisible(false);
        editDone.setVisible(false);

    }
}
