package entities.dao;

import entities.Book;
import entities.Client;
import entities.Loan;
import entities.Person;

import java.util.List;

public interface PersonDAO extends DAO<Person> {
    List<? extends Person> findByName(String name);
    Person findByLoan(Loan loan);
    Person findByBook(Book book);
}
