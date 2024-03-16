package org.example.model;

import com.jfoenix.controls.JFXButton;
import lombok.*;
import org.example.entity.Branch;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookTm {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private String availability;
    private int bookCount;

    public BookTm(String bookId, String title, String author, String genre, boolean availability,int bookCount, String branchName, JFXButton button) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability ? "available":"borrowed/returned";
        this.branchName = branchName;
        this.bookCount = bookCount;
        this.button = button;
    }

    private String branchName;
    private JFXButton button;

    public void setAvailability(boolean availability) {
        if (availability){
            this.availability = "available";
        }
        else {
            this.availability = "borrowed/not returned";
        }
    }
}
