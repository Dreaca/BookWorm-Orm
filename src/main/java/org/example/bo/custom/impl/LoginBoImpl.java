package org.example.bo.custom.impl;

import org.example.bo.custom.LoginBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.UserDao;
import org.example.entity.User;

public class LoginBoImpl implements LoginBo {
    UserDao dao = (UserDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.USER);

    @Override
    public boolean checkAdmin() {
        return dao.exists();
    }

    @Override
    public boolean validate(String userName, String password) {
        User search = dao.search(userName);
        return search.getPassWord().equals(password);
    }

    @Override
    public String checkUser(String userName) {
        User search = dao.search(userName);
        if (search.getUserId().startsWith("ADMIN")){
            return "ADMIN";
        }
        else return "USER";
    }
}
