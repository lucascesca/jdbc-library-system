package entities.dao.impl;

import db.DB;
import db.DbException;
import entities.Book;
import entities.Publisher;
import entities.dao.PublisherDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublisherDaoJDBC implements PublisherDAO {
    private Connection conn;

    public PublisherDaoJDBC(Connection conn) { this.conn = conn; }

    @Override
    public void insert(Publisher obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO publisher (code, publi_name) VALUES(?, ?);",
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId());
            pstmt.setString(2, obj.getName());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("No rows affected");
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
    public void update(Publisher obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "UPDATE publisher SET publi_name = ? WHERE code = ?;",
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
    public void delete(Publisher obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "DELETE FROM publisher WHERE code = ?;");

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
    public Publisher find(Publisher obj) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT P.CODE AS P_CODE, P.PUBLI_NAME, B.CODE AS B_CODE, B.TITLE, B.PUBLISHING_YEAR, B.EDITION FROM PUBLISHER P " +
                            "JOIN BOOK B ON (?=B.PUBLISHER_CODE);", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            pstmt.setInt(1, obj.getId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return instantiatePublisher(rs);
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

    private Publisher instantiatePublisher(ResultSet rs) throws SQLException{
        Publisher obj = new Publisher();
        obj.setId(rs.getInt("p_code"));
        obj.setName(rs.getString("publi_name"));
        rs.beforeFirst();
        while (rs.next()) {
            obj.addBook(instantiateBook(rs));
        }

        return obj;
    }

    private Book instantiateBook(ResultSet rs) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt("b_code"));
        book.setTitle(rs.getString("title"));
        book.setPublishing_year(rs.getDate("publishing_year"));
        book.setEdition(rs.getString("edition"));
        book.setPublisher(new Publisher(rs.getInt("p_code"), rs.getString("publi_name")));

        return book;
    }

    @Override
    public List<? extends Publisher> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT P.CODE AS P_CODE, P.PUBLI_NAME, B.CODE AS B_CODE, B.TITLE, B.PUBLISHING_YEAR, B.EDITION FROM PUBLISHER P " +
                            "JOIN BOOK B ON (P.CODE=B.PUBLISHER_CODE);", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            List<Publisher> list = new ArrayList<>();

            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(instantiatePublisher(rs));
            }

            return list;

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(pstmt);
        }
    }

    @Override
    public Publisher findByBook(Book book) {
        return null;
    }

    @Override
    public Publisher findByName(String name) {
        return null;
    }
}
