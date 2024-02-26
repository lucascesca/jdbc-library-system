package entities.dao;

import entities.Book;
import entities.Client;
import entities.Loan;

public interface LoanDAO extends DAO<Loan> {
    Loan findByClient(Client client);
    Loan findByBook(Book book);
}
