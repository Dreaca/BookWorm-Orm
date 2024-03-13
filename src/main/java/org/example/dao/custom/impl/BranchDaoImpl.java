package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BranchDao;
import org.example.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class BranchDaoImpl implements BranchDao {
    @Override
    public String getNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String branchId = "";
        Query query = session.createQuery("select branchId from Branch order by branchId desc limit 1");
        Object o =query.uniqueResult();
        if (o == null){
            branchId =  "Branch01";
        }
        else {
            String user = o.toString();
            String[] userIds = user.split("Branch");
            int id = Integer.parseInt(userIds[1]);
            id++;
            branchId = String.format("Branch%01d",id);
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
}
