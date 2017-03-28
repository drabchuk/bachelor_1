package model;

/**
 * Created by Denis on 21.03.2017.
 */
public enum Currency {

    UAH("UAH"), USD("USD");

    private String chifer;

    Currency(String chifer) {
        this.chifer = chifer;
    }

    public String getChifer() {
        return chifer;
    }

    public static Currency byChifer(String chifer) {
        if (chifer.equals("UAH")) {
            return UAH;
        } else if (chifer.equals("USD")) {
            return USD;
        } else {
            return null;
        }
    }
}
