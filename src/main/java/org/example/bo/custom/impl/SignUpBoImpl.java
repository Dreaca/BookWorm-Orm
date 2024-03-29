package org.example.bo.custom.impl;

import org.example.bo.custom.SignUpBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.UserDao;
import org.example.dto.UserDto;
import org.example.entity.User;

public class SignUpBoImpl implements SignUpBo {

    UserDao dao = (UserDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.USER);

    @Override
    public boolean saveUser(UserDto userDto) {
        String userId = dao.getUserId();
        boolean b = dao.saveUser(new User(userId, userDto.getName(), userDto.getUserName(), userDto.getEmail(), userDto.getPassword()));
       return b;
    }

    @Override
    public boolean saveAdmin(UserDto userDto) {
        String adminId = dao.getAdminId();
        boolean b = dao.saveUser(new User(adminId, userDto.getName(), userDto.getUserName(), userDto.getEmail(), userDto.getPassword()));
        return b;
    }
}
