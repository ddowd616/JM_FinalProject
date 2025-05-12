package mil.swf.jm_finalproject.service;

import mil.swf.jm_finalproject.DTO.CountryDTO;
import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.entity.CurrencyCode;
import mil.swf.jm_finalproject.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryDTO> getAllCountries(){
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(country -> {
                    String currencyName = Optional.ofNullable(country.getCurrency())
                            .map(CurrencyCode::getCurrencyName)
                            .orElse("Unknown Currency");
                    String currencyCode = Optional.ofNullable(country.getCurrency())
                            .map(CurrencyCode::getCurrencyCode)
                            .orElse("N/A");
                    return new CountryDTO(
                            country.getCountryId(),
                        country.getCountryName(),
                        currencyName,
                        currencyCode,
                        country.getCurrencyCountryUses()
                    );
                }).collect(Collectors.toList());
    }

    public Optional<CountryDTO> getCountryByID(Long id) {
        Optional<Country> country = countryRepository.findById(id);
        return country.map(c -> new CountryDTO(
                c.getCountryId(),
                c.getCountryName(),
                c.getCurrency().getCurrencyName(),
                c.getCurrency().getCurrencyCode(),
                c.getCurrencyCountryUses()
        ));
    }
}
