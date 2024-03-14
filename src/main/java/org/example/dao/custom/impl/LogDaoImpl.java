package org.example.dao.custom.impl;

import javafx.scene.control.Alert;
import org.example.config.FactoryConfiguration;
import org.example.dao.custom.LogDao;
import org.example.entity.Branch;
import org.example.entity.Log;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class LogDaoImpl implements LogDao {
    @Override
    public List<Log> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Log> logList;
        try {
            logList = session.createQuery("FROM Log ", Log.class).list();
        }
        catch (Exception exception){
            new Alert(Alert.AlertType.WARNING,"NO data in Branches").show();
            logList = new ArrayList<>();
        }

        transaction.commit();
        session.close();

        return logList;
    }

    @Override
    public List<Log> getAllFor(String text) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Log> logList;
        try {
            Query<Log> query = session.createQuery("FROM Log where user.userId = ?1", Log.class);
            query.setParameter(1,text);
           logList = query.list();
        }
        catch (Exception exception){
            new Alert(Alert.AlertType.WARNING,"NO data in Branches").show();
            logList = new ArrayList<>();
        }

        transaction.commit();
        session.close();

        return logList;
    }

    @Override
    public String getNextTid() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String logTid = "";
        Query query = session.createQuery("select transactionId  from Log  where transactionId  like ?1 order by transactionId  desc limit 1");
        query.setParameter(1, "T%");
        Object o = query.uniqueResult();
        if (o == null) {
            logTid = "T001";
        } else {
            String user = o.toString();
            System.out.println(user);
            String[] userIds = user.split("T");
            int id = Integer.parseInt(userIds[1]);
            id++;
            logTid = String.format("T%03d", id);
        }
        transaction.commit();
        session.close();
        return logTid;
    }

    @Override
    public void save(Log log) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(log);
        transaction.commit();
        session.close();
    }
}
