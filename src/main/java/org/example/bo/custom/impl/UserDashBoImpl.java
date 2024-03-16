package org.example.bo.custom.impl;

import jakarta.transaction.Transactional;
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

import java.time.LocalDate;
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
                            b.getBookCount(),
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

    @Transactional
    @Override
    public boolean addLog(String bookId, String text) {
        String Tid = log.getNextTid();
        Book search = dao.search(bookId);
        User searched = user.search(text);
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = currentDate.plusDays(7);

        Log newLog = new Log(Tid, search, searched,
                currentDate,
                expirationDate,
                false);
        try {
        log.save(newLog);
        dao.updateAvailabilityFalse(search);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void updateUser(UserDto userDto) {
        user.update(new User(
           userDto.getUserId(),
           userDto.getUserName(),
           userDto.getName(),
           userDto.getEmail(),
           userDto.getPassword()
        ));
    }

    @Override
    public BookDto searchBookByName(String search) {
       Book book =  dao.searchByName(search);
       return new BookDto(
               book.getBookId(),
               book.getTitle(),
               book.getAuthor(),
               book.getGenre(),
               book.isAvailability(),
               book.getBookCount(),
               book.getBranch().getLocation()
               );
    }

    @Override
    public BookDto searchBook(String bookId) {
        Book b = dao.search(bookId);
        System.out.println(b.getTitle());
        return new BookDto(
        );
    }
}
