package org.example.dao.custom;

import org.example.dao.SuperDao;
import org.example.entity.User;

public interface UserDao extends SuperDao {
    String getUserId();

    boolean saveUser(User user);

    boolean exists();

    User search(String userName);

    String getAdminId();

}
