package mil.swf.jm_finalproject.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id")
    private CurrencyCode currency;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Column(name = "currency_country_uses", nullable = false)
    private String currencyCountryUses;

    protected Country(){

    }

    public Country(CurrencyCode currency, String countryName, String currencyCountryUses) {
        this.currency = currency;
        this.countryName = countryName;
        this.currencyCountryUses = currencyCountryUses;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public CurrencyCode getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyCode currency) {
        this.currency = currency;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrencyCountryUses() {
        return currencyCountryUses;
    }

    public void setCurrencyCountryUses(String currencyCountryUses) {
        this.currencyCountryUses = currencyCountryUses;
    }
}
