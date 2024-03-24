package entities.dao.impl;

import db.DB;
import db.DbException;
import entities.BookCopy;
import entities.dao.BookCopyDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookCopyDaoJDBC implements BookCopyDAO {
    Connection conn;

    public BookCopyDaoJDBC(Connection conn) { this.conn = conn; }
    @Override
    public void insert(BookCopy obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO book_copy(copy_code, book_code) VALUES(?, ?);",
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getPk().getCopyId());
            pstmt.setInt(2, obj.getPk().getBookId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    obj.setPk(rs.getInt("copy_code"), rs.getInt("book_code"));
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
    public void update(BookCopy obj1, BookCopy obj2) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "UPDATE book_copy SET copy_code = ?, book_code = ? WHERE copy_code = ? AND book_code = ?;",
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj2.getPk().getCopyId());
            pstmt.setInt(2, obj2.getPk().getBookId());
            pstmt.setInt(3, obj1.getPk().getCopyId());
            pstmt.setInt(4, obj1.getPk().getBookId());

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
    public void delete(BookCopy obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "DELETE FROM book_copy WHERE copy_code = ? AND book_code = ?;");

            pstmt.setInt(1, obj.getPk().getCopyId());
            pstmt.setInt(2, obj.getPk().getBookId());

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
    public BookCopy find(BookCopy obj) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT copy_code, book_code FROM book_copy " +
                    "WHERE copy_code = ? AND book_code = ?;");

            pstmt.setInt(1, obj.getPk().getCopyId());
            pstmt.setInt(2, obj.getPk().getBookId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new BookCopy(rs.getInt("copy_code"), rs.getInt("book_code"));
            }

            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage() + e.getCause());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pstmt);
        }
    }

    @Override
    public List<? extends BookCopy> findAllCopies(Integer bookId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT copy_code, book_code FROM book_copy " +
                            "WHERE book_code = ?;");

            pstmt.setInt(1, bookId);

            rs = pstmt.executeQuery();

            List<BookCopy> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new BookCopy(rs.getInt("copy_code"), rs.getInt("book_code")));
            }

            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage() + e.getCause());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pstmt);
        }
    }


}
