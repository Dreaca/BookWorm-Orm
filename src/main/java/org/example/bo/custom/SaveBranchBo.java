package org.example.bo.custom;

import org.example.dto.BranchDto;

public interface SaveBranchBo extends SuperBo{
    String getNextBranchId();

    boolean saveBranch(BranchDto branchDto);
}
