package db.dao.oracle;

import db.dao.GdpDAO;
import db.dao.OracleDAOFactory;
import model.AnnualGDPelement;
import model.Country;
import model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis on 16.12.2016.
 */
public class OracleGDPDAO implements GdpDAO {

    public List<AnnualGDPelement> getAnnualGDP(Country country, int fromYear, int toYear) throws SQLException{
        LinkedList<AnnualGDPelement> result = new LinkedList<>();
        try (Connection con = OracleDAOFactory.createConnection()
             ; PreparedStatement ps =
                     con.prepareStatement(
                             "SELECT * FROM ANNUAL_GDP WHERE" +
                                     " country_chifer = ? " +
                                     "AND year >= ? " +
                                     "AND year <= ? " +
                                     "ORDER BY year"
                     )
        ) {
            ps.setString(1, country.getChifer());
            ps.setInt(2, fromYear);
            ps.setInt(3, toYear);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                double real = rs.getDouble("real_value");
                double chained = rs.getDouble("chained_value");
                int basicYear = rs.getInt("basic_year");
                int year = rs.getInt("year");
                Currency cur = Currency.byChifer(rs.getString("currency"));
                result.add(new AnnualGDPelement(country, year, cur, real, chained, basicYear));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
        return result;
    }

    public int insertData(List<AnnualGDPelement> data)throws SQLException {
        int i = 0;
        try (Connection con = OracleDAOFactory.createConnection()
             ; PreparedStatement ps =
                     con.prepareStatement(
                             "INSERT INTO ANNUAL_GDP VALUES(?,?,?,?,?,?)"
                     )
        ) {
            for (AnnualGDPelement e: data) {
                ps.setString(1, e.getCountry().getChifer());
                ps.setInt(2, e.getYear());
                ps.setString(3, e.getCurrency().getChifer());
                ps.setDouble(4, e.getRealGDP());
                ps.setDouble(5, e.getChainedGDP());
                ps.setInt(6, e.getBasicYear());
                i += ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
        return i;
    }

}
