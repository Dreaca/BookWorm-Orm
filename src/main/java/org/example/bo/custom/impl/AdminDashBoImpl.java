package org.example.bo.custom.impl;

import jakarta.transaction.Transactional;
import org.example.bo.custom.AdminDashBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.BooksDao;
import org.example.dao.custom.BranchDao;
import org.example.dao.custom.LogDao;
import org.example.dao.custom.UserDao;
import org.example.dto.BookDto;
import org.example.dto.LogDto;
import org.example.dto.UserDto;
import org.example.entity.Book;
import org.example.entity.Branch;
import org.example.entity.Log;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;

public class AdminDashBoImpl implements AdminDashBo {
    BranchDao dao = (BranchDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.BRANCH);
    UserDao userDao = (UserDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.USER);
    BooksDao booksDao = (BooksDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.BOOKS);
    LogDao lgdao = (LogDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.LOG);
    @Override
    public List<Branch> getAllBranches() {
        List<Branch> all = dao.getAll();
        return all;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> all = userDao.getAll();
        return all;
    }

    @Override
    public void deleteBranch(String branchId) {
        dao.deleteBranch(branchId);
    }

    @Override
    public List<BookDto> getAllBooksofThisBranch(String branchId) {
        List<Book> branchBooks = booksDao.getBranchBooks(branchId);
        List<BookDto> list = new ArrayList<>();
        for(Book b : branchBooks){
            list.add(
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
        return list;
    }

    @Override
    public List<LogDto> getAllLogs() {
        List<Log> all = lgdao.getAll();
        List<LogDto> list = new ArrayList<>();
        for(Log b : all){
            list.add(
                    new LogDto(
                            b.getTransactionId(),
                            b.getBook().getTitle(),
                            b.getUser().getUserName(),
                            b.getBorrowDate(),
                            b.getReturnDate(),
                            b.isStatus()
                    )
            );
        }
        return list;
    }

    @Override
    public void updateUser(UserDto userDto) {
        userDao.update(new User(
                userDto.getUserId(),
                userDto.getUserName(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword()
        ));
    }

    @Override
    public UserDto getUser(String userName) {
        User search = userDao.search(userName);
        return new UserDto(search.getUserId(),search.getName(),search.getUserName(),search.getPassWord(),search.getEmail());
    }
    @Transactional
    @Override
    public void updateTransaction(String tid) {
        Book update = lgdao.update(tid);
        booksDao.updateAvailability(update);
    }

    @Override
    public void deleteUser(String userId) {
        userDao.delete(userId);
    }

    @Override
    public List<LogDto> getOverDueList() {
        List<Log> all = lgdao.getOverDueList();
        List<LogDto> list = new ArrayList<>();
        for(Log b : all){
            list.add(
                    new LogDto(
                            b.getTransactionId(),
                            b.getBook().getTitle(),
                            b.getUser().getUserName(),
                            b.getBorrowDate(),
                            b.getReturnDate(),
                            b.isStatus()
                    )
            );
        }
        return list;
    }

    @Override
    public List<UserDto> getOverDueUsers() {
        List<User> overdueUsers = userDao.getOverdueUsers();
        List<UserDto> list = new ArrayList<>();
        for(User u : overdueUsers){
            list.add(
                    new UserDto(
                        u.getUserId(),
                        u.getUserName(),
                        u.getName(),
                            u.getEmail(),
                            u.getPassWord()
                    )
            );
        }
        return list;
    }
}
