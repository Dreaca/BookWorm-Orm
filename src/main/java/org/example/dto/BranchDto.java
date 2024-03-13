package org.example.dto;

public class BranchDto {
    private String location;
    private int bookNo;
    private String branchMan;

    public BranchDto( String location, int bookNo, String branchMan) {
        this.location = location;
        this.bookNo = bookNo;
        this.branchMan = branchMan;
    }

    public BranchDto() {
    }

    @Override
    public String toString() {
        return "BranchDto{" +
                ", location='" + location + '\'' +
                ", bookNo=" + bookNo +
                ", branchMan='" + branchMan + '\'' +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getBookNo() {
        return bookNo;
    }

    public void setBookNo(int bookNo) {
        this.bookNo = bookNo;
    }

    public String getBranchMan() {
        return branchMan;
    }

    public void setBranchMan(String branchMan) {
        this.branchMan = branchMan;
    }
}
