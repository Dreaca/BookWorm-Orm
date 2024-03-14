package org.example.dao.custom.impl;

import javafx.scene.control.Alert;
import org.example.config.FactoryConfiguration;
import org.example.dao.custom.LogDao;
import org.example.entity.Branch;
import org.example.entity.Log;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
