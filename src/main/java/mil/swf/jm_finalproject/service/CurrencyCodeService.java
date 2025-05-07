package mil.swf.jm_finalproject.service;

import mil.swf.jm_finalproject.entity.CurrencyCode;
import mil.swf.jm_finalproject.repository.CurrencyCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CurrencyCodeService {
    private final CurrencyCodeRepository repository;

    public CurrencyCodeService(CurrencyCodeRepository repository) {
        this.repository = repository;
    }

    public List<CurrencyCode> getAllCurrencyCodes() {
        return repository.findAll();
    }

    public Optional<CurrencyCode> getCurrencyCodeByCode(String code) {
        return repository.findByCurrencyCode(code);
        // THis is a new comment
    }
}
