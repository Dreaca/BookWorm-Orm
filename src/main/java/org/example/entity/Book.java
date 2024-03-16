package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
public class Book {
    @Id
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private boolean availability;
    private int bookCount;
    @ManyToOne
    @JoinColumn(name = "branchId",referencedColumnName = "branchId")
    private Branch branch;
    @OneToMany(mappedBy = "book")
    @Cascade(CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Log> logs;

    public Book(String bookId, String title, String author, String genre, boolean availability, int bookCount, Branch branch) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.bookCount = bookCount;
        this.branch = branch;
    }

    public Book() {
    }

    public Book(String bookId, String title, String author, String genre, boolean availability, Branch branch) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.branch = branch;
    }
}
