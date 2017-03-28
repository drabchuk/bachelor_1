import db.dao.excel.ExcelGDPDAO;
import model.AnnualGDPelement;
import model.Country;
import model.Currency;

import java.util.List;

/**
 * Created by Denis on 24.03.2017.
 */
public class ExcelGDPDAOTest {

    public static void main(String[] args) {
        List<AnnualGDPelement> annualGDPelements = ExcelGDPDAO.getRealAnnualGDP(
                "D:\\bachelor\\data\\USA_BEA\\national_accounts\\yearly\\GDP\\gdplev.xls",
                Country.USA,
                Currency.USD,
                8, 95,
                0,
                1,
                2,
                2009
        );
        System.out.println(annualGDPelements.get(0).getCountry());
        System.out.println(annualGDPelements.get(0).getCurrency());
        System.out.println(annualGDPelements.get(0).getBasicYear());
        for (AnnualGDPelement e: annualGDPelements) {
            System.out.println(e.getYear() + " " + e.getRealGDP() + " " + e.getChainedGDP());
        }
    }

}
