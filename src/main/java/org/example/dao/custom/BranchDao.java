package org.example.dao.custom;

import org.example.dao.SuperDao;
import org.example.entity.Branch;

public interface BranchDao extends SuperDao {
    String getNextId();

    void saveBranch(Branch branch);
}
