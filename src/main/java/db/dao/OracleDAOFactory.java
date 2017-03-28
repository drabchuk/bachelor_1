package db.dao;

import db.dao.oracle.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Denis on 09.12.2016.
 */
public final class OracleDAOFactory extends DAOFactory {

    public static Connection createConnection() throws SQLException{
        DAOSingleton dao = DAOSingleton.getInstance();
        return DriverManager.getConnection(dao.DB_URL, dao.USER, dao.PASS);
    }

    @Override
    public GdpDAO getGDPDAO() {
        return new OracleGDPDAO();
    }

}
