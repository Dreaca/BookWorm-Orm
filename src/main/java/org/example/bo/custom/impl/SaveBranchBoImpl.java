package org.example.bo.custom.impl;

import org.example.bo.custom.SaveBranchBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.BranchDao;
import org.example.dto.BranchDto;
import org.example.entity.Branch;

public class SaveBranchBoImpl implements SaveBranchBo {

    private BranchDao dao = (BranchDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.BRANCH);
    @Override
    public String getNextBranchId() {
        return dao.getNextId();
    }

    @Override
    public boolean saveBranch(BranchDto branchDto) {
        String branchid = getNextBranchId();
        dao.saveBranch(new Branch(branchid
        , branchDto.getLocation(),branchDto.getBookNo(),branchDto.getBranchMan()
        ));
        return true;
    }
}
