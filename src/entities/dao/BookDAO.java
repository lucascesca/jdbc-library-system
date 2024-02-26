package entities.dao;

import entities.Book;
import entities.Client;
import entities.Loan;

import java.util.List;

public interface BookDAO extends DAO<Book> {
    List<? extends Book> findByClient(Client client);
    Book findByTitle(String title);
    Book findByLoan(Loan loan);
}
