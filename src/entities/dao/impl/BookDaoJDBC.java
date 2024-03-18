package entities.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import entities.Book;
import entities.Client;
import entities.Loan;
import entities.dao.BookDAO;

import java.sql.*;
import java.util.List;

public class BookDaoJDBC implements BookDAO {
    private Connection conn;

    public BookDaoJDBC(Connection conn) { this.conn = conn; }

    @Override
    public void insert(Book obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO book(code, publishing_year, title, edition, publisher_code)" +
                            "VALUES (?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId());
            pstmt.setDate(2, new java.sql.Date(obj.getPublishing_year().getTime()));
            pstmt.setString(3, obj.getTitle());
            pstmt.setString(4, obj.getEdition());
            pstmt.setInt(5, obj.getPublisher().getId());

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
    public void update(Book obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "UPDATE book SET publishing_year = ?, title = ?, edition = ?, publisher_code = ? WHERE code = ?");

            pstmt.setDate(1, new java.sql.Date(obj.getPublishing_year().getTime()));
            pstmt.setString(2, obj.getTitle());
            pstmt.setString(3, obj.getEdition());
            pstmt.setInt(4, obj.getPublisher().getId());
            pstmt.setInt(5, obj.getId());

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
    public void delete(Book obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    " DELETE FROM book WHERE code = ?;");

            pstmt.setInt(1, obj.getId());

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbIntegrityException("Esta ação viola a restrinção de integridade");
        }
        finally {
            DB.closeStatement(pstmt);
        }
    }

    @Override
    public Book find(Book obj) {
        return null;
    }

    @Override
    public List<? extends Book> findAll() {
        return null;
    }

    @Override
    public List<? extends Book> findByClient(Client client) {
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        return null;
    }

    @Override
    public Book findByLoan(Loan loan) {
        return null;
    }
}
