package org.example.dto;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LogDto {
    private String Tid;
    private String bookName;
    private String userName;
    private LocalDate borrwedDate;
    private LocalDate returnedDate;
    private boolean status;

    public LogDto(String tid, String bookName, LocalDate borrwedDate, LocalDate returnedDate, boolean status) {
        Tid = tid;
        this.bookName = bookName;
        this.borrwedDate = borrwedDate;
        this.returnedDate = returnedDate;
        this.status = status;
    }
}
