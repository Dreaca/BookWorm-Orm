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
}
