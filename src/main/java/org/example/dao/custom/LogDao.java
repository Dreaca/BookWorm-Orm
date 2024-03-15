package org.example.dao.custom;

import org.example.dao.SuperDao;
import org.example.entity.Book;
import org.example.entity.Log;

import java.util.List;

public interface LogDao extends SuperDao {
    List<Log> getAll();

    List<Log> getAllFor(String text);

    String getNextTid();

    void save(Log log) throws Exception;

    Book update(String tid);

    Log search(String tid);

    List<Log> getOverDueList();

    void delete(String tid);
}
