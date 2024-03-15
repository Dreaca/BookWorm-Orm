package org.example.bo.custom;

import org.example.dto.BookDto;

public interface BooksBo extends SuperBo{
    String getABookId();

    void saveThisBook(BookDto bookDto);

    void updateThisBook(BookDto bookDto);
}
