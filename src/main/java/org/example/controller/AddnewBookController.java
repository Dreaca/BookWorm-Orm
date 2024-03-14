package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.SaveBooksBo;
import org.example.dto.BookDto;

public class AddnewBookController {

    @FXML
    private CheckBox isAvailable;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookTitle;

    @FXML
    private Label txtBranchId;

    @FXML
    private Label txtBranchName;

    @FXML
    private TextField txtGenre;

    private SaveBooksBo bo = (SaveBooksBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.BOOKS);

    @FXML
    void cancelOnAction(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveBookOnAction(ActionEvent event) {
        String aBookId = bo.getABookId();
        String branchName = txtBranchName.getText();
        String bookTitle = txtBookTitle.getText();
        String authorText = txtAuthor.getText();
        String genreText = txtGenre.getText();
        boolean availability = isAvailable.isSelected();

        if (!authorText.matches("^[a-zA-Z\\s.'-]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid author").show();
            return;
        }

        if (!bookTitle.matches("^[a-zA-Z0-9\\s.'-]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid book title").show();
            return;
        }

        if (!genreText.matches("^[a-zA-Z\\s,.'-]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid genre").show();
            return;
        }

        bo.saveThisBook(new BookDto(aBookId,bookTitle,authorText,genreText,availability,branchName));

    }
    public void setBranchAndName(String branchId, String branchName){
        this.txtBranchId.setText(branchId);
        this.txtBranchName.setText(branchName);
    }

}
