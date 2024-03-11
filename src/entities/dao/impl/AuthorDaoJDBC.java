package entities.dao.impl;

import db.DB;
import db.DbException;
import entities.Author;
import entities.Book;
import entities.Person;
import entities.dao.PersonDAO;

import java.sql.*;
import java.util.List;

public class AuthorDaoJDBC implements PersonDAO {
    Connection conn;

    public AuthorDaoJDBC(Connection conn) { this.conn = conn; }

    @Override
    public void insert(Person obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO author(code, author_name) VALUES (?, ?);",
                    Statement.RETURN_GENERATED_KEYS);

            Author obj1 = ((Author) obj);

            pstmt.setInt(1, obj1.getId());
            pstmt.setString(2, obj1.getName());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj1.setId(id);
                }
                DB.closeResultSet(rs);
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(pstmt);
        }
    }

    @Override
    public void update(Person obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Person findById(Integer id) {
        return null;
    }

    @Override
    public List<? extends Person> findAll() {
        return null;
    }

    @Override
    public List<? extends Person> findByName(String name) {
        return null;
    }

    @Override
    public Person findByBook(Book book) {
        return null;
    }
}
