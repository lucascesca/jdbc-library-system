package entities.dao;

import db.DB;
import entities.dao.impl.AddressDaoJDBC;
import entities.dao.impl.BookDaoJDBC;
import entities.dao.impl.ClientDaoJDBC;
import entities.dao.impl.PublisherDaoJDBC;

public class DaoFactory {
    public static ClientDaoJDBC createClientDaoJDBC() { return new ClientDaoJDBC(DB.getConnection()); }

    public static AddressDaoJDBC createAddressDaoJDBC() { return new AddressDaoJDBC(DB.getConnection()); }

    public static PublisherDaoJDBC createPublisherDaoJDBC() { return new PublisherDaoJDBC(DB.getConnection()); }

    public static BookDaoJDBC createBookDaoJDBC() { return new BookDaoJDBC(DB.getConnection()); }
}
