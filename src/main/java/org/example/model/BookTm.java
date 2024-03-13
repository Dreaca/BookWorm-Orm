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
    private boolean availability;

    private String branchName;
    private JFXButton button;
}
