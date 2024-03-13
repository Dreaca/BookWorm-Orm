package org.example.dao.custom;

import org.example.dao.SuperDao;
import org.example.entity.Book;

import java.util.List;

public interface BooksDao extends SuperDao {
    List<Book> getAll();

    List<Book> getBranchBooks(String branchId);
}
