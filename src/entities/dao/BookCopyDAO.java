package entities.dao;

import db.DB;
import db.DbException;
import entities.BookCopy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BookCopyDAO {
     void insert(BookCopy obj);

     void update(BookCopy obj1, BookCopy obj2);

     void delete(BookCopy obj);

     BookCopy find(BookCopy obj);

     List<? extends BookCopy> findAllCopies(Integer bookId);
}
