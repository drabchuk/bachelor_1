package db.dao;

import model.AnnualGDPelement;
import model.Country;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 09.12.2016.
 */
public interface GdpDAO {

    List<AnnualGDPelement> getAnnualGDP(Country country,
                                        int fromYear,
                                        int toYear) throws SQLException;
    
    int insertData(List<AnnualGDPelement> data) throws SQLException;

}
