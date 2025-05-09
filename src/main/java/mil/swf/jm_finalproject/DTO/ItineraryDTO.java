package mil.swf.jm_finalproject.DTO;

import java.time.LocalDate;

public class ItineraryDTO {

    private Long id;
    private Long userId;
    private Long countryId;

    private int orderOnTrip;
    private Boolean countryOfOrigin;

    private LocalDate startDate;
    private LocalDate endDate;

    private int daysSpentInCountry;
    private Boolean userWantsCurrencyExchangeRate;

    public ItineraryDTO() {
    }

    public ItineraryDTO(Long id, Long userId, Long countryId, int orderOnTrip, Boolean countryOfOrigin, LocalDate startDate, LocalDate endDate, int daysSpentInCountry, Boolean userWantsCurrencyExchangeRate) {
        this.id = id;
        this.userId = userId;
        this.countryId = countryId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
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
