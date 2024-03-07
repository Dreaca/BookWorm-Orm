package org.example.bo.custom.impl;

import org.example.bo.custom.LoginBo;
import org.example.dao.DaoFactory;
import org.example.dao.UserDao;

public class LoginBoImpl implements LoginBo {
    UserDao dao = (UserDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.USER);

    @Override
    public boolean checkAdmin() {
        return dao.exists();
    }
}