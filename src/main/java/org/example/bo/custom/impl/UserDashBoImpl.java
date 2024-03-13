package org.example.bo.custom.impl;

import org.example.bo.custom.UserDashBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.BooksDao;
import org.example.dto.BookDto;
import org.example.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class UserDashBoImpl implements UserDashBo {
    private BooksDao dao = (BooksDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.BOOKS);
    @Override
    public List<BookDto> getAllBooks() {
        List<Book> all = dao.getAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book b : all){
            bookDtos.add(
                    new BookDto(
                        b.getBookId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getGenre(),
                        b.isAvailability(),
                            b.getBranch().getLocation()
                    )
            );
        }
        return bookDtos;
    }
}
