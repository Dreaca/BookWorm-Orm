package org.example.model;

import com.jfoenix.controls.JFXButton;
import lombok.*;


import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LogTm {
    private String Tid;
    private String bookName;
    private String userName;
    private LocalDate borrwedDate;
    private LocalDate returnedDate;

    private boolean status;

    private JFXButton mod;
}
