package entities.dao.impl;

import db.DB;
import db.DbException;
import entities.Address;
import entities.dao.AddressDAO;
import entities.enums.States;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoJDBC implements AddressDAO {

    private Connection conn = null;

    public AddressDaoJDBC(Connection conn) { this.conn = conn; }

    @Override
    public void insert(Address obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO address(code, number, add_type, complement, city, uf, person_code, street)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getId());
            pstmt.setInt(2, obj.getNumber());
            pstmt.setString(3, obj.getType());
            pstmt.setString(4, obj.getComplement());
            pstmt.setString(5, obj.getCity());
            pstmt.setString(6, obj.getUf().toString());
            pstmt.setInt(7, obj.getClientId());
            pstmt.setString(8, obj.getStreet());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
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
    public void update(Address obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "UPDATE address SET number = ?, add_type = ?, complement = ?, city = ?, uf = ?, street = ? " +
                            "WHERE code = ?",
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, obj.getNumber());
            pstmt.setString(2, obj.getType());
            pstmt.setString(3, obj.getComplement());
            pstmt.setString(4, obj.getCity());
            pstmt.setString(5, obj.getUf().toString());
            pstmt.setString(6, obj.getStreet());
            pstmt.setInt(7, obj.getId());

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
    public void delete(Address obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "DELETE FROM address WHERE code = ?");

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
    public Address find(Address obj) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT * FROM address WHERE code = ?;");

            pstmt.setInt(1, obj.getId());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return instantiateAddress(rs);
            }
            return null;
        }
        catch ( SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pstmt);
        }
    }

    private Address instantiateAddress(ResultSet rs) throws SQLException {
        Address obj = new Address();
        obj.setId(rs.getInt("code"));
        obj.setUf(States.valueOf(rs.getString("uf")));
        obj.setStreet(rs.getString("street"));
        obj.setCity(rs.getString("city"));
        obj.setNumber(rs.getInt("number"));
        obj.setComplement(rs.getString("complement"));
        obj.setType(rs.getString("add_type"));
        obj.setClientId(rs.getInt("person_code"));

        return obj;
    }

    @Override
    public List<Address> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT * FROM address ORDER BY code;");

            rs = pstmt.executeQuery();

            List<Address> list = new ArrayList<>();

            while (rs.next()) {
                Address obj = instantiateAddress(rs);
                list.add(obj);
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
}