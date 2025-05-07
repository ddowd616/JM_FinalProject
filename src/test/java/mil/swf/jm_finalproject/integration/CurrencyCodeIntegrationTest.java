package mil.swf.jm_finalproject.integration;

import mil.swf.jm_finalproject.JmFinalProjectApplication;
import mil.swf.jm_finalproject.entity.CurrencyCode;
import mil.swf.jm_finalproject.repository.CurrencyCodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = JmFinalProjectApplication.class)
@AutoConfigureMockMvc
public class CurrencyCodeIntegrationTest {

    @Autowired
    private CurrencyCodeRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        repository.deleteAll();
        repository.save(new CurrencyCode("US Dollar","USD"));
        repository.save(new CurrencyCode("Euro","EUR"));
    }

    @Test
    void testGetAllCurrencyCodes() throws Exception{
        mockMvc.perform(get("/api/currency-codes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()",is(2)))
                .andExpect(jsonPath("$[0].currencyCode",anyOf(is("USD"),is("EUR"))));
    }
}
