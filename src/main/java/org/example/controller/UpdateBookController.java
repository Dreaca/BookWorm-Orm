package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.BooksBo;
import org.example.dto.BookDto;

public class UpdateBookController {
    private String bookId;
    public AnchorPane root;
    public Label txtBranchId;
    public Label txtBranchName;
    public TextField txtBookTitle;
    public TextField txtAuthor;
    public TextField txtGenre;
    public CheckBox isAvailable;

    BooksBo bo = (BooksBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.BOOKS);

    public void updateBookOnAction(ActionEvent event) {
        String aBookId = bookId;
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

        bo.updateThisBook(new BookDto(aBookId,bookTitle,authorText,genreText,availability,branchName));

    }

    public void cancelOnAction(ActionEvent event) {
       Stage stage = (Stage) root.getScene().getWindow();
       stage.close();
    }

    public void setBookID(BookDto book) {
        this.bookId = book.getBookId();
        txtBookTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        txtGenre.setText(book.getGenre());
        isAvailable.setSelected(book.isAvailability());
    }

    public void setBranchAndName(String branchId, String branchName) {
        txtBranchId.setText(branchId);
        txtBranchName.setText(branchName);
    }
}
