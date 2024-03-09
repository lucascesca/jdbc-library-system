package entities.dao;

import entities.Book;
import entities.Publisher;

public interface PublisherDAO extends DAO<Publisher> {
    Publisher findByBook(Book book);
    Publisher findByName(String name);
}
