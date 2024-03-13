package org.example.bo.custom;

import org.example.entity.Branch;
import org.example.entity.User;

import java.util.List;

public interface AdminDashBo extends SuperBo{
    List<Branch> getAllBranches();

    List<User> getAllUsers();

    void deleteBranch(String branchId);
}