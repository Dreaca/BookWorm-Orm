package org.example.bo.custom.impl;

import org.example.bo.custom.SaveBooksBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.BooksDao;
import org.example.dao.custom.BranchDao;
import org.example.dto.BookDto;
import org.example.entity.Book;
import org.example.entity.Branch;

public class SaveBooksBoImpl implements SaveBooksBo {
    BranchDao branch = (BranchDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.BRANCH);
    BooksDao dao = (BooksDao) DaoFactory.getDaoFactory().getDao(DaoFactory.Daotype.BOOKS);
    @Override
    public String getABookId() {
        return dao.getNextBookId();
    }

    @Override
    public void saveThisBook(BookDto bookDto) {
        String branchName = bookDto.getBranchName();
        Branch b = branch.getBranch(branchName);
        dao.save(new Book(
                bookDto.getBookId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getGenre(),
                bookDto.isAvailability(),
                b
        ));
    }
}
