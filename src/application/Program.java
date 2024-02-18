package application;

import db.DB;
import entities.Address;
import entities.Person;
import entities.enums.States;

import java.sql.Connection;

public class Program {
    public static void main(String[] args) {
        Connection conn = DB.getConection();

        Address ad1 = new Address(1, "Rua 2", 32, "Casa", null, "Rio de Janeiro", States.RJ);
        System.out.println(ad1);

        Person p1 = new Person(1, "Lucas", "Cesca", "123456789-10", "12.345.678", ad1);
        System.out.println(p1);

        DB.closeConnection();
    }
}
