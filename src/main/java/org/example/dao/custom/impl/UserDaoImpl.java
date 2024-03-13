package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.UserDao;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import static java.lang.String.format;

public class UserDaoImpl implements UserDao {
    @Override
    public String getUserId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String userId = "";
        Query query = session.createQuery("select userId from User where userId like ?1 order by userId desc limit 1");
        query.setParameter(1, "USER%");
        Object o = query.uniqueResult();
        if (o == null) {
            userId = "USER1";
        } else {
            String user = o.toString();
            String[] userIds = user.split("USER");
            int id = Integer.parseInt(userIds[1]);
            id++;
            userId = String.format("USER%01d", id);
        }
        transaction.commit();
        session.close();
        return userId;
    }

    @Override
    public boolean saveUser(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exists() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" select userId from User order by userId asc limit 1");
        Object singleResult = query.getSingleResult();
        transaction.commit();
        session.close();
        if (!String.valueOf(singleResult).startsWith("ADMIN")) {
            return false;
        } else return true;

    }

    @Override
    public User search(String userName) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where userName = ?1");
        query.setParameter(1, userName);
        Object o = query.getSingleResult();
        transaction.commit();
        session.close();
        return (User) o;
    }

    @Override
    public String getAdminId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String userId = "";
        Query query = session.createQuery("select userId  from User where userId like ?1 order by userId desc limit 1 ");
        query.setParameter(1, "ADMIN%");
        Object o = query.uniqueResult();
        if (o == null) {
            userId = "ADMIN01";
        } else {
            String user = o.toString();
            String[] userIds = user.split("ADMIN");
            int id = Integer.parseInt(userIds[1]);
            id++;
            userId = String.format("ADMIN%01d", id);
        }
        transaction.commit();
        session.close();
        return userId;
    }
}
