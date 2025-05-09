package mil.swf.jm_finalproject.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "itinerary")
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country country;

    private int orderOnTrip;
    private Boolean countryOfOrigin;
    private LocalDate startDate;
    private LocalDate endDate;
    private int daysSpentInCountry;
    private Boolean userWantsCurrencyExchangeRate;

    public Itinerary() {
    }

    public Itinerary(UserInfo userInfo, Country country, int orderOnTrip, Boolean countryOfOrigin, LocalDate startDate, LocalDate endDate, int daysSpentInCountry, Boolean userWantsCurrencyExchangeRate) {
        this.userInfo = userInfo;
        this.country = country;
        this.orderOnTrip = orderOnTrip;
        this.countryOfOrigin = countryOfOrigin;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysSpentInCountry = daysSpentInCountry;
        this.userWantsCurrencyExchangeRate = userWantsCurrencyExchangeRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getOrderOnTrip() {
        return orderOnTrip;
    }

    public void setOrderOnTrip(int orderOnTrip) {
        this.orderOnTrip = orderOnTrip;
    }

    public Boolean getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(Boolean countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getDaysSpentInCountry() {
        return daysSpentInCountry;
    }

    public void setDaysSpentInCountry(int daysSpentInCountry) {
        this.daysSpentInCountry = daysSpentInCountry;
    }

    public Boolean getUserWantsCurrencyExchangeRate() {
        return userWantsCurrencyExchangeRate;
    }

    public void setUserWantsCurrencyExchangeRate(Boolean userWantsCurrencyExchangeRate) {
        this.userWantsCurrencyExchangeRate = userWantsCurrencyExchangeRate;
    }
}
