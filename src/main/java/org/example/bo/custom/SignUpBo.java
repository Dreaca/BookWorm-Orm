package org.example.bo.custom;

import org.example.dto.UserDto;

public interface SignUpBo extends SuperBo {
    boolean saveUser(UserDto userDto);
}
