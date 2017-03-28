package model;

/**
 * Created by Denis on 21.03.2017.
 */
public class AnnualGDPelement {

    private Country country;
    private int year;
    private Currency currency;
    private double realGDP;
    private double chainedGDP;
    private int basicYear;

    public AnnualGDPelement(Country country, int year, 
                            Currency currency, double realGDP, 
                            double chainedGDP, int basicYear) 
    {
        this.country = country;
        this.year = year;
        this.currency = currency;
        this.realGDP = realGDP;
        this.chainedGDP = chainedGDP;
        this.basicYear = basicYear;
    }

    public Country getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getRealGDP() {
        return realGDP;
    }

    public double getChainedGDP() {
        return chainedGDP;
    }

    public int getBasicYear() {
        return basicYear;
    }
}
