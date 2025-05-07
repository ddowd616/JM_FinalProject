package mil.swf.jm_finalproject.repository;

import mil.swf.jm_finalproject.entity.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyCodeRepository extends JpaRepository<CurrencyCode, Long> {
    Optional<CurrencyCode> findByCurrencyCode(String currencyCode);
}
