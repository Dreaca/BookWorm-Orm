package org.example.dao.custom;

import org.example.dao.SuperDao;
import org.example.entity.Book;

import java.util.List;

public interface BooksDao extends SuperDao {
    List<Book> getAll();

    List<Book> getBranchBooks(String branchId);

    String getNextBookId();

    void save(Book book);

    Book search(String bookId);
}
