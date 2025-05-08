package mil.swf.jm_finalproject.service;

import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.entity.CurrencyCode;
import mil.swf.jm_finalproject.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class CountryServiceTest {

    private CountryRepository countryRepository;
    private CountryService countryService;

    @BeforeEach
    void setUp(){
        countryRepository = Mockito.mock(CountryRepository.class);
        countryService = new CountryService(countryRepository);
    }

    @Test
    void testGetAllCountries(){
        CurrencyCode usd = new CurrencyCode("USD","US Dollar");
        Country usa = new Country(usd, "United States", "USD");
        CurrencyCode gbp = new CurrencyCode("GPB","British Pound Sterling");
        Country uk = new Country(gbp, "United Kingdom", "GPB");

        when(countryRepository.findAll()).thenReturn(Arrays.asList(usa,uk));

        var result = countryService.getAllCountries();

        assertEquals(2,result.size());
        assertEquals("United States", result.get(0).getCountryName());
        assertEquals("GPB", result.get(1).getCurrencyCountryUses());
        verify(countryRepository,times(1)).findAll();
    }

    @Test
    void testGetCountryByCountryId_Found(){
        CurrencyCode usd = new CurrencyCode("US Dollar","USD");
        Country usa = new Country(usd, "United States", "US Dollar");
        usa.setCountryId(1L);

        when(countryRepository.findById(1L)).thenReturn(Optional.of(usa));

        var result = countryService.getCountryByID(1L);

        assertTrue(result.isPresent());
        var dto = result.get();
        assertEquals("United States", dto.getCountryName());
        assertEquals("US Dollar", dto.getCurrencyName());
        assertEquals("USD", dto.getCurrencyCode());
        assertEquals("US Dollar", dto.getCurrencyCountryUses());
    }

    @Test
    void testGetCountryByCountryId_NotFound(){
        when(countryRepository.findById(2L)).thenReturn(Optional.empty());

        var result = countryService.getCountryByID(2L);

        assertTrue(result.isEmpty());
    }


}
