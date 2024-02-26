package entities.dao;

import entities.Book;
import entities.Publisher;

public interface PublisherDAO {
    Publisher findByBook(Book book);
    Publisher findByName(String name);
}
