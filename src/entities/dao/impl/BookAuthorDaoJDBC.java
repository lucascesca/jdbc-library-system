package entities.dao.impl;

import db.DB;
import db.DbException;
import entities.BookAuthor;
import entities.dao.BookAuthorDAO;

import java.sql.*;
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

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public BookAuthor findById(Integer id) {
        return null;
    }

    @Override
    public List<? extends BookAuthor> findAll() {
        return null;
    }
}
