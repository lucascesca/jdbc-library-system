package entities.dao;

import entities.Author;
import entities.Book;
import entities.BookAuthor;
import entities.Person;

import java.util.List;

public interface BookAuthorDAO  {
    void insert(BookAuthor obj);
    void update(BookAuthor obj);
    <T> void delete(Integer id, T obj);
    public List<Book> findBooksByAuthor(String name);
    public List<Author> findAuthorsByBook(String name);
}
