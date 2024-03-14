package org.example.dao.custom.impl;

import javafx.scene.control.Alert;
import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BooksDao;
import org.example.entity.Book;
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
        System.out.println(branchId);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Book> bookList;
        try {
            Query<Book> query = session.createQuery("FROM Book where branch.branchId = ?1", Book.class);
            query.setParameter(1,branchId);
            bookList = query.list();
        }
        catch (Exception exception){
            new Alert(Alert.AlertType.WARNING,"NO data in Books").showAndWait();
            bookList = new ArrayList<>();
        }

        transaction.commit();
        session.close();

        return bookList;
    }

    @Override
    public String getNextBookId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String bookId = "";
        Query query = session.createQuery("select bookId from Book order by bookId desc limit 1");
        Object o = query.uniqueResult();
        if (o == null) {
            bookId = "BOOK01";
        } else {
            String user = o.toString();
            String[] userIds = user.split("BOOK");
            int id = Integer.parseInt(userIds[1]);
            id++;
            bookId = String.format("BOOK%01d", id);
        }
        transaction.commit();
        session.close();
        return bookId;
    }

    @Override
    public void save(Book book) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(book);
        transaction.commit();
        session.close();
    }

    @Override
    public Book search(String bookId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Book load = session.load(Book.class, bookId);
        transaction.commit();
        session.close();
        return load;
    }
}
