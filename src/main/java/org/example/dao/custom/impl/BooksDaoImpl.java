package org.example.dao.custom.impl;

import javafx.scene.control.Alert;
import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BooksDao;
import org.example.entity.Book;
import org.example.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class BooksDaoImpl implements BooksDao {
    @Override
    public List<Book> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Book> bookList;
        try {
            bookList = session.createQuery("FROM Book", Book.class).list();
        }
        catch (Exception exception){
            new Alert(Alert.AlertType.WARNING,"NO data in Books").show();
            bookList = new ArrayList<>();
        }

        transaction.commit();
        session.close();

        return bookList;
    }

    @Override
    public List<Book> getBranchBooks(String branchId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Book> bookList;
        try {
            Query<Book> query = session.createQuery("FROM Book where branch.branchId= ?1", Book.class);
            query.setParameter(1,branchId);
            bookList = query.list();
        }
        catch (Exception exception){
            new Alert(Alert.AlertType.WARNING,"NO data in Books").show();
            bookList = new ArrayList<>();
        }

        transaction.commit();
        session.close();

        return bookList;
    }
}
