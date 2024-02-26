package entities.dao;

import entities.Loan;
import entities.Person;

import java.util.List;

public interface PersonDAO extends DAO<Person> {
    List<Person> findByName(String name);
    Person findByLoan(Loan loan);
}
