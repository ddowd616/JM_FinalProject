package mil.swf.jm_finalproject.controller;

import mil.swf.jm_finalproject.DTO.ItineraryDTO;
import mil.swf.jm_finalproject.service.ItineraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItineraryController.class)
public class ItineraryControllerTest {

    @MockitoBean
    private ItineraryService itineraryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateItineraryEntry() throws Exception {
        ItineraryDTO savedItinerary = new ItineraryDTO(
                100L,
                7L,
                65L,
                3,
                true,
                LocalDate.of(2025,6,1),
                LocalDate.of(2025,6,5),
                5,
                false
        );

        when(itineraryService.createItineraryEntry(any(ItineraryDTO.class))).thenReturn(savedItinerary);

        mockMvc.perform(post("/api/itineraries/create")
                .contentType("application/json")
                .content("""
                            {
                                "userId": 7,
                                "countryId": 2,
                                "orderOnTrip": 1,
                                "countryOfOrigin": false,
                                "startDate": "2025-06-01",
                                "endDate": "2025-06-05",
                                "daysSpentInCountry": 5,
                                "userWantsCurrencyExchangeRate": false
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.userId").value(7))
                .andExpect(jsonPath("$.countryId").value(65))
                .andExpect(jsonPath("$.daysSpentInCountry").value(5))
                .andExpect(jsonPath("$.userWantsCurrencyExchangeRate").value(false));
    }
}
