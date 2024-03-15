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
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.AdminDashBo;
import org.example.dto.BookDto;
import org.example.model.BookTm;

import java.io.IOException;
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
    void addNewBookOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addNewBook.fxml"));
        Object load = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) load);
        stage.setScene(scene);
        AddnewBookController controller = loader.getController();
        controller.setBranchAndName(txtBranchId.getText(),txtBranchName.getText());
        stage.show();
    }

    public void setBranchNameId(String branchId,String branchName){
        System.out.println(1);
        txtBranchId.setText(branchId);
        txtBranchName.setText(branchName);
        loadBooks();
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
        ObservableList<BookTm> oblist = FXCollections.observableArrayList();
        List<BookDto> list = bo.getAllBooksofThisBranch(txtBranchId.getText());
        System.out.println(2);
        for(BookDto dto : list){
            oblist.add(
                    new BookTm(
                            dto.getBookId(),
                            dto.getTitle(),
                            dto.getAuthor(),
                            dto.getGenre(),
                            dto.isAvailability(),
                            dto.getBranchName(),
                            getModButton()
                    )
            );
        }
        booktbl.setItems(oblist);
        booktbl.refresh();
        for (int i = 0; i < oblist.size(); i++) {
            int finalI = i;
            int finalI2 = i;
            int finalI1 = i;
            oblist.get(i).getButton().setOnAction(event -> {
                try {
                    JFXButton bt = oblist.get(finalI).getButton();
                    double x = bt.localToScreen(bt.getBoundsInLocal()).getMinX();
                    double y = bt.localToScreen(bt.getBoundsInLocal()).getMinY();

                    ContextMenu con =  loadPopup(oblist.get(finalI).getButton());

                    con.getItems().get(0).setOnAction(actionEvent1 -> {
                        try {
                            loadUpdateBook(oblist.get(finalI1));
                            loadBooks();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    con.getItems().get(1).setOnAction(actionEvent1 -> {
                            bo.deleteBook(oblist.get(finalI).getBookId());
                            loadBooks();
                    });
                    con.show(bt, x, y);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void loadUpdateBook(BookTm book) throws IOException {
        BookDto dto = new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getAvailability()=="available",
                txtBranchId.getText()
        );


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/updateBook.fxml"));
        Object load = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) load);
        stage.setScene(scene);
        UpdateBookController controller = loader.getController();
        controller.setBookID(dto);
        controller.setBranchAndName(txtBranchId.getText(),txtBranchName.getText());
        stage.show();
    }

    private JFXButton getModButton() {
        return new JFXButton("options");
    }
    public ContextMenu loadPopup(JFXButton modifyButton) throws IOException {
        ContextMenu con = new ContextMenu();
        MenuItem button1 = new MenuItem("Update");
        MenuItem button2 = new MenuItem("Delete");
        con.getItems().addAll(button1, button2);

        return con;
    }

}
