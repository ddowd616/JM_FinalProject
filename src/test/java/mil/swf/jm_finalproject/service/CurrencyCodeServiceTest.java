package mil.swf.jm_finalproject.service;

import mil.swf.jm_finalproject.entity.CurrencyCode;
import mil.swf.jm_finalproject.repository.CurrencyCodeRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CurrencyCodeServiceTest {
    private final CurrencyCodeRepository repository = mock(CurrencyCodeRepository.class);
    private final CurrencyCodeService service = new CurrencyCodeService(repository);

    @Test
    void testGetAllCurrencyCodes(){
        CurrencyCode usd = new CurrencyCode("US Dollar","USD");
        CurrencyCode eur = new CurrencyCode("Euro","EUR");
        when(repository.findAll()).thenReturn(Arrays.asList(usd,eur));

        var result = service.getAllCurrencyCodes();

        assertEquals(2,result.size());
        verify(repository,times(1)).findAll();
    }

    @Test
    void testGetCurrencyCodeByCode_Found(){
        CurrencyCode usd = new CurrencyCode("US Dollar","USD");
        when(repository.findByCurrencyCode("USD")).thenReturn(Optional.of(usd));

        var result = service.getCurrencyCodeByCode("USD");

        assertTrue(result.isPresent());
        assertEquals("US Dollar",result.get().getCurrencyName());
    }

    @Test
    void testGetCurrencyCodeByCode_NotFound(){
        when(repository.findByCurrencyCode("ABC")).thenReturn(Optional.empty());

        var result = service.getCurrencyCodeByCode("ABC");

        assertTrue(result.isEmpty());

    }
}
