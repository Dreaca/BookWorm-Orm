package org.example.dao.custom;

import org.example.dao.SuperDao;
import org.example.entity.Branch;

import java.util.List;

public interface BranchDao extends SuperDao {
    String getNextId();

    void saveBranch(Branch branch);

    List<Branch> getAll();

    void deleteBranch(String branchId);
}
