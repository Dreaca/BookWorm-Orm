package org.example.bo.custom;

import org.example.dto.BookDto;
import org.example.dto.LogDto;
import org.example.dto.UserDto;

import java.util.List;

public interface UserDashBo extends SuperBo{
    List<BookDto> getAllBooks();

    UserDto getUser(String userName);

    List<LogDto> getAllLogsForThisUser(String text);

    boolean addLog(String bookId, String text);

    void updateUser(UserDto userDto);
}
