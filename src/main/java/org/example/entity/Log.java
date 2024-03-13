package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Log {
    @Id
    private String transactionId;
    @JoinColumn(referencedColumnName = "bookId")
    @ManyToOne
    private Book book;
    @ManyToOne
    private User user;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean status;

}