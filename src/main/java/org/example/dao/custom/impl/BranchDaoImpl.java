package org.example.dao.custom.impl;

import javafx.scene.control.Alert;
import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BranchDao;
import org.example.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class BranchDaoImpl implements BranchDao {
    @Override
    public String getNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String branchId = "";
          Query query = session.createQuery("select branchId from Branch order by branchId desc limit 1");
          Object o = query.uniqueResult();
          if (o == null) {
              branchId = "Branch01";
          } else {
              String user = o.toString();
              String[] userIds = user.split("Branch");
              int id = Integer.parseInt(userIds[1]);
              id++;
              branchId = String.format("Branch%01d", id);
          }
        transaction.commit();
        session.close();
        return branchId;
    }

    @Override
    public void saveBranch(Branch branch) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(branch);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Branch> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Branch> branches;
        try {
            branches = session.createQuery("FROM Branch", Branch.class).list();
        }
        catch (Exception exception){
            new Alert(Alert.AlertType.WARNING,"NO data in Branches").show();
            branches = new ArrayList<>();
        }

        transaction.commit();
        session.close();

        return branches;
    }

    @Override
    public void deleteBranch(String branchId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Branch load = session.load(Branch.class, branchId);
        session.delete(load);
        transaction.commit();
        session.close();
    }

    @Override
    public Branch getBranch(String branchName) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Branch where location = ?1 order by branchId desc limit 1");
        query.setParameter(1,branchName);
        Branch branch = (Branch) query.getSingleResult();
        transaction.commit();
        session.close();
        return branch;
    }

}
