package mil.swf.jm_finalproject.DTO;

import java.time.LocalDate;

public class UserInfoDTO {
    private Long userId;
    private Long countryId;
    private String userName;
    private String userPassword;
    private LocalDate dateOfBirth;

    public UserInfoDTO() {
    }

    public UserInfoDTO(Long userId, Long countryId, String userName, String userPassword, LocalDate dateOfBirth) {
        this.userId = userId;
        this.countryId = countryId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.dateOfBirth = dateOfBirth;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
