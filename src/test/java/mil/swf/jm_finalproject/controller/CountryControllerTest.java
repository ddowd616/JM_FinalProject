package mil.swf.jm_finalproject.controller;

import mil.swf.jm_finalproject.DTO.CountryDTO;
import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.entity.CurrencyCode;
import mil.swf.jm_finalproject.service.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

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
    void testGetAllCountries() throws Exception {
        var usa = new Country(new CurrencyCode("US Dollar", "USD"),"United States","US Dollar");
        var uk = new Country(new CurrencyCode("British Pound Sterling","GPB"),"United Kingdom","British Pound Sterling");
        var usaDTO = new CountryDTO("United States","US Dollar","USD","US Dollar");
        var ukDTO = new CountryDTO("United Kingdom", "British Pound Sterling","GPB","British Pound Sterling");
        when(countryService.getAllCountries()).thenReturn(List.of(usaDTO,ukDTO));

        mockMvc.perform(get("/api/country"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].countryName").value("United States"))
                .andExpect(jsonPath("$[0].currencyName").value("US Dollar"))
                .andExpect(jsonPath("$[1].currencyCountryUses").value("British Pound Sterling"))
                .andExpect(jsonPath("$[1].currencyCode").value("GPB"));

    }

    @Test
    void testGetCountryByCountryId_Found() throws Exception {

        var jap = new Country(new CurrencyCode("Japanese Yen","JPY"),"Japan","Yen");
        jap.setCountryId(1L);
        var countryDTO = new CountryDTO("Japan","Japanese Yen", "JPY","Yen");
        when(countryService.getCountryByID(1L)).thenReturn(Optional.of(countryDTO));

        mockMvc.perform(get("/api/country/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyName").value("Japanese Yen"))
                .andExpect(jsonPath("$.currencyCode").value("JPY"))
                .andExpect(jsonPath("$.countryName").value("Japan"))
                .andExpect(jsonPath("$.currencyCountryUses").value("Yen"));
    }

    @Test
    void testGetCountryByCountryId_NotFound() throws Exception {
        when(countryService.getCountryByID(600L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/country/600"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }

}
