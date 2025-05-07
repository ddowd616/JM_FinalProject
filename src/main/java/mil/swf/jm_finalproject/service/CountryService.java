package mil.swf.jm_finalproject.service;

import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    public Optional<Country> getCountryByID(Long id) {
        return countryRepository.findById(id);
    }
}
