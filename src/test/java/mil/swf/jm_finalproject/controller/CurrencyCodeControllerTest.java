package mil.swf.jm_finalproject.controller;

import mil.swf.jm_finalproject.entity.CurrencyCode;
import mil.swf.jm_finalproject.service.CurrencyCodeService;
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

@WebMvcTest(CurrencyCodeController.class)
public class CurrencyCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private CurrencyCodeService service;

    @Test
    void testGetAllCurrencyCodes() throws Exception{
        var usd = new CurrencyCode("US Dollar","USD");
        var eur = new CurrencyCode("Euro","EUR");
        when(service.getAllCurrencyCodes()).thenReturn(List.of(usd,eur));

        mockMvc.perform(get("/api/currency-codes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].currencyName").value("US Dollar"))
                .andExpect(jsonPath("$[1].currencyCode").value("EUR"));
    }

    @Test
    void testGetCurrencyCodeByCode_Found() throws Exception{
        var jpy = new CurrencyCode("Japanese Yen", "JPY");
        when(service.getCurrencyCodeByCode("JPY")).thenReturn(Optional.of(jpy));

        mockMvc.perform(get("/api/currency-codes/JPY"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyName").value("Japanese Yen"))
                .andExpect(jsonPath("$.currencyCode").value("JPY"));
    }
    @Test
    void testGetCurrencyCodeByCode_NotFound() throws Exception{
        when(service.getCurrencyCodeByCode("ABC")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/currency-codes/ABC"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }

}
