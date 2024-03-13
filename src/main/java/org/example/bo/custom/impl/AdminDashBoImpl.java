package org.example.bo.custom.impl;

import org.example.bo.custom.AdminDashBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.BranchDao;
import org.example.dao.custom.UserDao;
import org.example.entity.Branch;
import org.example.entity.User;

import java.util.List;

public class AdminDashBoImpl implements AdminDashBo {
    BranchDao dao = (BranchDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.BRANCH);
    UserDao userDao = (UserDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.USER);
    @Override
    public List<Branch> getAllBranches() {
        List<Branch> all = dao.getAll();
        return all;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> all = userDao.getAll();
        return all;
    }

    @Override
    public void deleteBranch(String branchId) {
        dao.deleteBranch(branchId);
    }
}
