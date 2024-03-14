package org.example.bo.custom;

import org.example.dto.BookDto;

public interface SaveBooksBo extends SuperBo{
    String getABookId();

    void saveThisBook(BookDto bookDto);
}
