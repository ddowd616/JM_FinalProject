package mil.swf.jm_finalproject.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country countryOfOrigin;
    private String userName;
    private String userPassword;
    private LocalDate dateOfBirth;

    public UserInfo() {
    }

    public UserInfo(Country countryOfOrigin, String userName, String userPassword, LocalDate dateOfBirth) {
        this.countryOfOrigin = countryOfOrigin;
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

    public Country getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(Country countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
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
