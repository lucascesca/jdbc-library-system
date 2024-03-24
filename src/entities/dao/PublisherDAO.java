package entities.dao;

import entities.Book;
import entities.Publisher;

public interface PublisherDAO extends DAO<Publisher> {
    void delete(Publisher obj);

    Publisher find(Publisher obj);

    Publisher findByBook(Book book);
    Publisher findByName(String name);
}
