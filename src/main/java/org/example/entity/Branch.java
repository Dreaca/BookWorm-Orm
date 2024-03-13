package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Branch {
    @Id
    private String branchId;
    private String  location;
    private int noOfBooks;
    private String branchManager;

    public Branch() {
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
