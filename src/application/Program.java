package application;

import db.DB;
import entities.Person;

import java.sql.Connection;

public class Program {
    public static void main(String[] args) {
        Connection conn = DB.getConection();

        Person p1 = new Person(1, "Lucas", "Cesca", "123456789-10", "12.345.678");
        System.out.println(p1);

        DB.closeConnection();
    }
}
