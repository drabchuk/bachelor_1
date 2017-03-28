package model;
import static model.Currency.*;

/**
 * Created by Denis on 21.03.2017.
 */
public enum Country {

    UKRAINE(UAH, "UKR"), USA(USD, "USA");

    private Currency currency;
    private String chifer;

    Country(Currency currency, String chifer) {
        this.currency = currency;
        this.chifer = chifer;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getChifer() {
        return chifer;
    }

    public static Country byChifer(String chifer) {
        if (chifer.equals("UKR")) {
            return UKRAINE;
        } else if (chifer.equals("USA")) {
            return USA;
        } else {
            return null;
        }
    }

}
