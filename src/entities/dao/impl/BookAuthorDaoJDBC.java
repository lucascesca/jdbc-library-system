package entities.dao.impl;

import db.DB;
import db.DbException;
import entities.Author;
import entities.Book;
import entities.BookAuthor;
import entities.Publisher;
import entities.dao.BookAuthorDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorDaoJDBC implements BookAuthorDAO {
    Connection conn;

    public BookAuthorDaoJDBC(Connection conn) { this.conn = conn; }

    @Override
    public void insert(BookAuthor obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO book_author (author_code, book_code) VALUES (?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, obj.getPk().getAuthor().getId());
            pstmt.setInt(2, obj.getPk().getBook().getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int author_id = rs.getInt("author_code");
                    int book_id = rs.getInt("book_code");
                    obj.getPk().getAuthor().setId(author_id);
                    obj.getPk().getBook().setId(book_id);
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
    public void update(BookAuthor obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "UPDATE book_author SET book_code = ? WHERE author_code = ?;",
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getPk().getBook().getId());
            pstmt.setInt(2, obj.getPk().getAuthor().getId());

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
    public <T> void delete(Integer id, T obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = obj instanceof Book ?
                    conn.prepareStatement("DELETE FROM book_author WHERE book_code = ?;") :
                    conn.prepareStatement("DELETE FROM book_author WHERE author_code = ?;");

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(pstmt);
        }
    }

    public List<Book> findBooksByAuthor(String name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT b.code AS book_id, publishing_year AS py, title AS tl, edition AS ed, publisher_code AS p_id, pb.publi_name AS p_name " +
                            "FROM author au " +
                            "JOIN book_author ba ON (au.code=ba.author_code) " +
                            "JOIN book b ON (b.code=ba.book_code) " +
                            "JOIN publisher pb ON (b.publisher_code=pb.code) " +
                            "WHERE LOWER(au.author_name) LIKE LOWER(?);");
            pstmt.setString(1, "%" + name + "%");

            rs = pstmt.executeQuery();
            List<Book> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateBook(rs));
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

    public List<Author> findAuthorsByBook(String name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT au.code AS author_code, author_name " +
                            "FROM author au " +
                            "JOIN book_author ba ON (au.code=ba.author_code) " +
                            "JOIN book b ON (b.code=ba.book_code) " +
                            "JOIN publisher pb ON (b.publisher_code=pb.code) " +
                            "WHERE LOWER(b.title) LIKE LOWER(?);");

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
    private Book instantiateBook(ResultSet rs) throws SQLException{
        Book obj = new Book();

        obj.setId(rs.getInt("book_id"));
        obj.setTitle(rs.getString("tl"));
        obj.setPublishing_year(rs.getDate("py"));
        obj.setEdition(rs.getString("ed"));
        obj.setPublisher(new Publisher(rs.getInt("p_id"), rs.getString("p_name")));

        return obj;
    }

    private Author instantiateAuthor(ResultSet rs) throws SQLException {
        return new Author(rs.getInt("author_code"), rs.getString("author_name"));
    }
}
