package mil.swf.jm_finalproject.DTO;

public class CountryDTO {
    private String countryName;
    private String currencyName;
    private String currencyCode;
    private String currencyCountryUses;

    public CountryDTO(String countryName, String currencyName, String currencyCode, String currencyCountryUses) {
        this.countryName = countryName;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.currencyCountryUses = currencyCountryUses;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCountryUses() {
        return currencyCountryUses;
    }

    public void setCurrencyCountryUses(String currencyCountryUses) {
        this.currencyCountryUses = currencyCountryUses;
    }
}
