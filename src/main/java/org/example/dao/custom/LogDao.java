package org.example.dao.custom;

import org.example.dao.SuperDao;
import org.example.entity.Log;

import java.util.List;

public interface LogDao extends SuperDao {
    List<Log> getAll();
}