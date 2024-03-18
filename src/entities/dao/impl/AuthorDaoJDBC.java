package entities.dao.impl;

import db.DB;
import db.DbException;
import entities.Author;
import entities.Book;
import entities.Person;
import entities.dao.PersonDAO;

import java.sql.*;
import java.util.ArrayList;
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
                    int id = rs.getInt("code");
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
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "UPDATE author SET author_name = ? WHERE code = ?;",
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, obj.getName());
            pstmt.setInt(2, obj.getId());

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(pstmt);
        }

    }

    @Override
    public void delete(Person obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "DELETE FROM author WHERE code = ?;");

            pstmt.setInt(1, obj.getId());

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(pstmt);
        }
    }

    @Override
    public Person find(Person obj) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT * FROM author WHERE code = ?;");
            pstmt.setInt(1, obj.getId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return instantiateAuthor(rs);
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pstmt);
        }
    }

    @Override
    public List<? extends Person> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT * FROM author;");

            rs = pstmt.executeQuery();

            List<Author> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateAuthor(rs));
            }

            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pstmt);
        }
    }

    private Author instantiateAuthor(ResultSet rs) throws SQLException {
        Author obj = new Author();

        obj.setId(rs.getInt("code"));
        obj.setName(rs.getString("author_name"));

        return obj;
    }

    @Override
    public List<? extends Person> findByName(String name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT * FROM author WHERE LOWER(author_name) LIKE LOWER(?);");

            pstmt.setString(1, "%" + name + "%");

            rs = pstmt.executeQuery();

            List<Author> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateAuthor(rs));
            }

            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pstmt);
        }
    }

    @Override
    public Person findByBook(Book book) {
        return null;
    }
}
