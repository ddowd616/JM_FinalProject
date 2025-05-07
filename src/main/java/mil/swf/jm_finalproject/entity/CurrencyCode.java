package mil.swf.jm_finalproject.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;


@Entity
@Immutable
@Table(name = "currency_code")
public class CurrencyCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private Long currencyId;
    @Column(name = "currency_name",nullable = false)
    private String currencyName;
    @Column(name = "currency_code",nullable = false)
    private String currencyCode;

    public CurrencyCode(String currencyName, String currencyCode) {
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
    }

    protected CurrencyCode() {
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
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
}
