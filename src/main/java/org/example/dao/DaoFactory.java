package org.example.dao;

import org.example.dao.custom.impl.BooksDaoImpl;
import org.example.dao.custom.impl.BranchDaoImpl;
import org.example.dao.custom.impl.LogDaoImpl;
import org.example.dao.custom.impl.UserDaoImpl;

public class DaoFactory {
    private DaoFactory(){}

    public static DaoFactory daoFactory;

    public static enum Daotype{
        USER,BRANCH,BOOKS,LOG
    }

    public SuperDao getDao(Daotype daotype){
        switch (daotype){
            case USER:
                return new UserDaoImpl();
            case BRANCH:
                return new BranchDaoImpl();
            case BOOKS:
                return new BooksDaoImpl();
            case LOG:
                return new LogDaoImpl();
            default:
                return null;
        }
    }
    public static DaoFactory getDaoFactory() {
        return daoFactory ==null?new DaoFactory():daoFactory;
    }
}
