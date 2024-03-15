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

    Book searchByName(String search);

    void updateAvailabilityFalse(Book search);

    void updateAvailability(Book search1);

    void update(Book book);

    void deleteBook(String bookId);
}
