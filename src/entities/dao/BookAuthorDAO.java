package entities.dao;

import entities.Book;
import entities.BookAuthor;

import java.util.List;

public interface BookAuthorDAO extends DAO<BookAuthor> {
    public List<Book> findBooksByAuthor(String name);
}
