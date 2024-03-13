package entities.dao;

import db.DB;
import entities.dao.impl.*;

public class DaoFactory {
    public static ClientDaoJDBC createClientDaoJDBC() { return new ClientDaoJDBC(DB.getConnection()); }

    public static AddressDaoJDBC createAddressDaoJDBC() { return new AddressDaoJDBC(DB.getConnection()); }

    public static PublisherDaoJDBC createPublisherDaoJDBC() { return new PublisherDaoJDBC(DB.getConnection()); }

    public static BookDaoJDBC createBookDaoJDBC() { return new BookDaoJDBC(DB.getConnection()); }

    public static AuthorDaoJDBC createAuthorDaoJDBC() { return new AuthorDaoJDBC(DB.getConnection()); }

    public static BookAuthorDaoJDBC createBookAuthorDaoJDBC() { return new BookAuthorDaoJDBC(DB.getConnection()); }
}
