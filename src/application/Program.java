package application;

import db.DB;
import entities.Address;
import entities.Author;
import entities.Client;
import entities.Person;
import entities.enums.States;

import java.sql.Connection;

public class Program {
    public static void main(String[] args) {
        Connection conn = DB.getConection();

        Address ad1 = new Address(1, "Rua 2", 32, "Casa", null, "Rio de Janeiro", States.RJ);
        System.out.println(ad1);

        Client p1 = new Client(1, "Lucas Cesca", "123456789-10", "12.345.678", ad1);
        System.out.println(p1);

        Author a1 = new Author(1, "Bram Stoker");
        System.out.println(a1.getName());

        System.out.println(p1.getName());

        DB.closeConnection();
    }
}
