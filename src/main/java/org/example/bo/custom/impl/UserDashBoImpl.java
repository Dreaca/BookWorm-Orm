package org.example.bo.custom.impl;

import org.example.bo.custom.UserDashBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.BooksDao;
import org.example.dao.custom.LogDao;
import org.example.dao.custom.UserDao;
import org.example.dto.BookDto;
import org.example.dto.LogDto;
import org.example.dto.UserDto;
import org.example.entity.Book;
import org.example.entity.Log;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDashBoImpl implements UserDashBo {
    private BooksDao dao = (BooksDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.BOOKS);
    private UserDao user = (UserDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.USER);

    private LogDao log = (LogDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.LOG);
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

    @Override
    public UserDto getUser(String userName) {
        User search = user.search(userName);
        return new UserDto(search.getUserId(),search.getName(),search.getUserName(),search.getPassWord(),search.getEmail());
    }

    @Override
    public List<LogDto> getAllLogsForThisUser(String text) {
        List<Log> allFor = log.getAllFor(text);
        List<LogDto> loglist = new ArrayList<>();
        for(Log dto : allFor){
            loglist.add(
                    new LogDto(
                        dto.getTransactionId(),
                        dto.getBook().getTitle(),
                        dto.getBorrowDate(),
                        dto.getReturnDate(),
                        dto.isStatus()
                    )
            );
        }
        return loglist;
    }
}
