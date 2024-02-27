package entities.dao;

import db.DB;
import entities.dao.impl.AddressDaoJDBC;
import entities.dao.impl.ClientDaoJDBC;

public class DaoFactory {
    public static ClientDaoJDBC createClientDaoJDBC() { return new ClientDaoJDBC(DB.getConection()); }

    public static AddressDaoJDBC createAddressDaoJDBC() { return new AddressDaoJDBC(DB.getConection()); }
}
