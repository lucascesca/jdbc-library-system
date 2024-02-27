package entities.dao.impl;

import db.DB;
import db.DbException;
import entities.*;
import entities.dao.PersonDAO;
import entities.enums.States;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoJDBC implements PersonDAO {

    private Connection conn;

    public ClientDaoJDBC(Connection conn) { this.conn = conn; }

    @Override
    public void insert(Person obj) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(
                    "INSERT INTO client(code, cpf, rg, client_name) VALUES (?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);

            Client obj1 = ((Client) obj);
            pstmt.setInt(1, obj1.getId());
            pstmt.setString(2, obj1.getCpf());
            pstmt.setString(3, obj1.getRg());
            pstmt.setString(4, obj.getName());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj1.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("No rows affected");
            }
        } catch (SQLException e) {
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
                    "UPDATE client SET cpf = ?, rg = ?, client_name = ? WHERE code = ?;",
                    Statement.RETURN_GENERATED_KEYS);

            Client obj1 = ((Client) obj);

            pstmt.setString(1, obj1.getCpf());
            pstmt.setString(2, obj1.getRg());
            pstmt.setString(3, obj1.getName());
            pstmt.setInt(4, obj1.getId());

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
    public void deleteById(Integer id) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("DELETE FROM client WHERE code = ?;");

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

    @Override
    public Person findById(Integer id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT C.CODE AS client_id, C.CPF, C.RG, C.CLIENT_NAME, " +
                            "A.CODE AS address_id, A.NUMBER, A.ADD_TYPE, A.COMPLEMENT, A.CITY, A.UF, A.STREET " +
                            "FROM CLIENT C " +
                            "JOIN ADDRESS A ON (C.CODE=A.PERSON_CODE) WHERE c.code = ?;");
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return instantiateClient(rs);
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

    private Client instantiateClient(ResultSet rs) throws SQLException {
        Client client = new Client();

        client.setId(rs.getInt("client_id"));
        client.setName(rs.getString("client_name"));
        client.setCpf(rs.getString("cpf"));
        client.setRg(rs.getString("rg"));
        client.setAddress(instantiateAddress(rs));

        return client;
    }

    private Address instantiateAddress(ResultSet rs) throws SQLException {
        Address address = new Address();

        address.setId(rs.getInt("address_id"));
        address.setCity(rs.getString("city"));
        address.setComplement(rs.getString("complement"));
        address.setNumber(rs.getInt("number"));
        address.setType(rs.getString("add_type"));
        address.setStreet(rs.getString("street"));
        address.setUf(States.valueOf(rs.getString("uf")));

        return address;
    }

    @Override
    public List<Client> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(
                    "SELECT C.CODE AS client_id, C.CPF, C.RG, C.CLIENT_NAME, " +
                            "A.CODE AS address_id, A.NUMBER, A.ADD_TYPE, A.COMPLEMENT, A.CITY, A.UF, A.STREET " +
                            "FROM CLIENT C " +
                            "JOIN ADDRESS A ON (C.CODE=A.PERSON_CODE);");

            rs = pstmt.executeQuery();

            List<Client> list = new ArrayList<>();

            while (rs.next()) {
                Client obj = instantiateClient(rs);
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

    @Override
    public List<Client> findByName(String name) {
        return null;
    }

    public Client findByLoan(Loan loan) {
        return null;
    }

    @Override
    public Person findByBook(Book book) {
        return null;
    }
}