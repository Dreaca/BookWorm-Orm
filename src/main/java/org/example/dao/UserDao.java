package org.example.dao;

import org.example.entity.User;

public interface UserDao extends SuperDao{
    String getUserId();

    boolean saveUser(User user);

    boolean exists();
}
