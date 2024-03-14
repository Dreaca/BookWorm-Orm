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

    private String  status;

    private JFXButton mod;

    public LogTm(String tid, String bookName, LocalDate borrwedDate, LocalDate returnedDate, boolean status) {
        Tid = tid;
        this.bookName = bookName;
        this.borrwedDate = borrwedDate;
        this.returnedDate = returnedDate;
        this.status = status? "returned":"Due";
    }

    public LogTm(String tid, String bookName, String userName, LocalDate borrwedDate, LocalDate returnedDate, boolean status, JFXButton mod) {
        Tid = tid;
        this.bookName = bookName;
        this.userName = userName;
        this.borrwedDate = borrwedDate;
        this.returnedDate = returnedDate;
        this.status = status? "returned":"due";
        this.mod = mod;
    }
}
