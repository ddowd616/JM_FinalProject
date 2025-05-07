package mil.swf.jm_finalproject.controller;

import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.entity.CurrencyCode;
import mil.swf.jm_finalproject.service.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    @MockitoBean
    private CountryService countryService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllCountries() throws Exception{
        var usa = new Country(new CurrencyCode("US Dollar", "USD"),"United States","US Dollar");
        var uk = new Country(new CurrencyCode("British Pound Sterling","GPB"),"United Kingdom","British Pound Sterling");
        when(countryService.getAllCountries()).thenReturn(List.of(usa,uk));

        mockMvc.perform(get("/api/country"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].countryName").value("United States"))
                .andExpect(jsonPath("$[0].currency.currencyName").value("US Dollar"))
                .andExpect(jsonPath("$[1].currencyCountryUses").value("British Pound Sterling"))
                .andExpect(jsonPath("$[1].currency.currencyCode").value("GPB"));

    }
}
