package db.dao;

/**
 * Created by Denis on 09.12.2016.
 */
public abstract class DAOFactory {

    public static final int ORACLE = 1;

    public abstract GdpDAO getGDPDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case ORACLE :
                return new OracleDAOFactory();
            default :
                return null;
        }
    }

}
