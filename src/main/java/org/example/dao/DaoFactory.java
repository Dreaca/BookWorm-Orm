package org.example.dao;

public class DaoFactory {
    private DaoFactory(){}

    public static DaoFactory daoFactory;

    public static enum Daotype{
        USER
    }

    public SuperDao getDao(Daotype daotype){
        switch (daotype){
            case USER:
                return new UserDaoImpl();
            default:
                return null;
        }
    }
    public static DaoFactory getDaoFactory() {
        return daoFactory ==null?new DaoFactory():daoFactory;
    }
}
