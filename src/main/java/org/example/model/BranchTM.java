package org.example.model;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchTM {
    private String branchId;
    private String  location;
    private String branchMan;
    private int bookNo;
    private JFXButton modButton;
    private JFXButton booksButton;
}
