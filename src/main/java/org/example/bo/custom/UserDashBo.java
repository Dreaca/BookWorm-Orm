package org.example.bo.custom;

import org.example.dto.BookDto;

import java.util.List;

public interface UserDashBo extends SuperBo{
    List<BookDto> getAllBooks();
}
