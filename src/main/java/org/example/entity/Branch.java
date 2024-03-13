package org.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.naming.Name;
import java.util.List;

@Entity
@Table
public class Branch {
    @Id
    private String branchId;
    private String  location;
    private int noOfBooks;
    private String branchManager;
    @OneToMany(mappedBy = "branch",orphanRemoval = true,cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Book> bookList;

    public Branch() {
    }

    public Branch(String branchId, String location, int noOfBooks, String branchManager, List<Book> bookList) {
        this.branchId = branchId;
        this.location = location;
        this.noOfBooks = noOfBooks;
        this.branchManager = branchManager;
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNoOfBooks() {
        return noOfBooks;
    }

    public void setNoOfBooks(int noOfBooks) {
        this.noOfBooks = noOfBooks;
    }

    public String getBranchManager() {
        return branchManager;
    }

    public void setBranchManager(String branchManager) {
        this.branchManager = branchManager;
    }

    public Branch(String branchId, String location, int noOfBooks, String branchManager) {
        this.branchId = branchId;
        this.location = location;
        this.noOfBooks = noOfBooks;
        this.branchManager = branchManager;
    }
}
