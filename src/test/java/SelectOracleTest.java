import db.dao.DAOSingleton;
import db.dao.GdpDAO;
import model.AnnualGDPelement;
import model.Country;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis on 24.03.2017.
 */
public class SelectOracleTest {

    public static void main(String[] args) {
        GdpDAO gdpDAO = DAOSingleton.getInstance().getGdpDAO();
        try {
            List<AnnualGDPelement> annualGDPelements = gdpDAO.getAnnualGDP(Country.USA, 1945, 2010);
            System.out.println(annualGDPelements.get(0).getCountry());
            System.out.println(annualGDPelements.get(0).getCurrency());
            System.out.println(annualGDPelements.get(0).getBasicYear());
            for (AnnualGDPelement e: annualGDPelements) {
                System.out.println(e.getYear() + " " + e.getRealGDP() + " " + e.getChainedGDP());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
