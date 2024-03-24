package entities.dao.impl;

import db.DB;
import db.DbException;
import entities.Book;
import entities.Client;
import entities.Loan;
import entities.dao.LoanDAO;

import java.sql.*;
import java.util.List;

public class LoanDaoJDBC implements LoanDAO {
    Connection conn;

    public LoanDaoJDBC(Connection conn) { this.conn = conn; }
    @Override
    public void insert(Loan obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO loan(code, loan_date, foreseen_return, edition_code, person_code) " +
                    "VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId());
            pstmt.setDate(2, new Date(obj.getLoanDate().getTime()));
            pstmt.setDate(3, new Date(obj.getPreviewReturnDate().getTime()));
            pstmt.setInt(4, obj.getCopy().getPk().getCopyId());
            pstmt.setInt(5, obj.getClient().getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt("code");
                    obj.setId(id);
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
    public void update(Loan obj) {

    }

    @Override
    public void delete(Loan obj) {

    }

    @Override
    public Loan find(Loan obj) {
        return null;
    }

    @Override
    public List<? extends Loan> findAll() {
        return null;
    }

    @Override
    public Loan findByClient(Client client) {
        return null;
    }

    @Override
    public Loan findByBook(Book book) {
        return null;
    }
}
