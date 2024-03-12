package application;

import entities.*;
import entities.dao.*;
import entities.enums.States;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        PersonDAO clientDao = DaoFactory.createClientDaoJDBC();
        AddressDAO addressDao = DaoFactory.createAddressDaoJDBC();
        PublisherDAO publisherDao = DaoFactory.createPublisherDaoJDBC();
        BookDAO bookDao = DaoFactory.createBookDaoJDBC();
        PersonDAO authorDao = DaoFactory.createAuthorDaoJDBC();

        Client c1 = new Client(1, "Lucas Cesca", "123456789-10", "12.345.678", null);
        Client c2 = new Client(2, "Marco Cesca", "372.444.160-67", "45.012.682", null);
        Address ad1 = new Address(1, "Rua 2", 35, "Casa", null, "Rio de Janeiro", States.RJ, c1.getId());
        Address ad2 = new Address(2, "Rua do Carmo", 80, "Casa", null, "São Paulo", States.SP, c2.getId());
        c1.setAddress(ad1);
        c2.setAddress(ad2);

        Publisher p1 = new Publisher(1, "Darkside");
        Book b1 = new Book(1, "Dracula", sdf.parse("1897-05-26"), "hard cover", p1);
        Book b2 = new Book(2, "The Lair of the White Worm", new Date(), "standard", p1);
        Book b3 = new Book(3, "Poe's poems", new Date(), "standard", p1);


        Client client = (Client) clientDao.findById(1);
        Address address = addressDao.findById(1);

        Author a1 = new Author(1, "Bram Stoker");
        Author a2 = new Author(2, "Edgar Allan Poe");

        //authorDao.insert(a1);
        //authorDao.update(a1);
        //authorDao.deleteById(1);

        System.out.println(authorDao.findById(1));
        System.out.println();

        System.out.println(authorDao.findAll());
        System.out.println(authorDao.findByName("Poe"));

        /*
        b1.addAuthor(List.of(a1));
        a1.addBooks(List.of(b1, b2));
        p1.addBooks(List.of(b1, b3));
        for (int i = 0; i < 5; i++) {
            b1.createCopies();
        }
        Loan l1 = new Loan(1, new Date(), new Date(), b1.getCopies().get(0), c1);

        c1.addClientLoan(l1);
        b1.getCopies().get(0).addLoan(l1);

        System.out.println(b1.getAuthors());
        System.out.println(a1.getBooks());
        System.out.println("Esta é lista de publicados " + p1.getBooks());
        System.out.println(b1.getCopies().get(0));
        System.out.println(l1);
        System.out.println("Livros empresitmos " + b1.getCopies().get(0).getLoans());
        System.out.println(c1.getLoans());*/

    }
}
