package mil.swf.jm_finalproject.controller;

import mil.swf.jm_finalproject.DTO.ItineraryDTO;
import mil.swf.jm_finalproject.service.ItineraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    void testGetItineraryEntryById_Found() throws Exception {
        var itineraryDTO = new ItineraryDTO(3L,4L,5L,6,true,LocalDate.of(2019,3,5),LocalDate.of(2019,3,8),3,false);
        when(itineraryService.getItineraryEntryById(3L)).thenReturn(itineraryDTO);
        mockMvc.perform(get("/api/itineraries/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.userId").value(4))
                .andExpect(jsonPath("$.countryId").value(5))
                .andExpect(jsonPath("$.orderOnTrip").value(6))
                .andExpect(jsonPath("$.countryOfOrigin").value(true))
                .andExpect(jsonPath("$.startDate").value("2019-03-05"))
                .andExpect(jsonPath("$.userWantsCurrencyExchangeRate").value(false));
    }

    @Test
    void testGetItineraryEntryById_NotFound() throws Exception {
        when(itineraryService.getItineraryEntryById(999L)).thenThrow(new RuntimeException("Itinerary Entry not found"));

        mockMvc.perform(get("/api/itineraries/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Itinerary Entry not found")));
    }

    @Test
    void testUpdateItineraryEntry_Success() throws Exception {
        ItineraryDTO updateDTO = new ItineraryDTO();
        updateDTO.setId(3L);
        updateDTO.setUserId(34L);
        updateDTO.setCountryId(42L);
        updateDTO.setOrderOnTrip(6);
        updateDTO.setCountryOfOrigin(false);
        updateDTO.setStartDate(LocalDate.of(2024,4,5));
        updateDTO.setEndDate(LocalDate.of(2024,4,9));
        updateDTO.setDaysSpentInCountry(4);
        updateDTO.setUserWantsCurrencyExchangeRate(true);

        when(itineraryService.updateItineraryEntry(eq(3L),any(ItineraryDTO.class))).thenReturn(updateDTO);

        mockMvc.perform(put("/api/itineraries/3")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                          "userId": 34,
                          "countryId": 42,
                          "orderOnTrip": 6,
                          "countryOfOrigin": false,
                          "startDate": "2024-04-05",
                          "endDate": "2024-04-09",
                          "daysSpentInCountry": 4,
                          "userWantsCurrencyExchangeRate": true
                        }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.userId").value(34))
                .andExpect(jsonPath("$.countryId").value(42))
                .andExpect(jsonPath("$.countryOfOrigin").value(false))
                .andExpect(jsonPath("$.startDate").value("2024-04-05"))
                .andExpect(jsonPath("$.endDate").value("2024-04-09"))
                .andExpect(jsonPath("$.daysSpentInCountry").value(4))
                .andExpect(jsonPath("$.userWantsCurrencyExchangeRate").value(true));
    }

    @Test
    void testUpdateItinerary_NotFound() throws Exception {
        when(itineraryService.updateItineraryEntry(eq(1L),any(ItineraryDTO.class))).thenThrow(new RuntimeException("Itinerary Entry not found"));

        mockMvc.perform(put("/api/itineraries/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "userId": 34,
                          "countryId": 42,
                          "orderOnTrip": 6,
                          "countryOfOrigin": false,
                          "startDate": "2024-04-05",
                          "endDate": "2024-04-09",
                          "daysSpentInCountry": 4,
                          "userWantsCurrencyExchangeRate": true
                        }
                    """))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Itinerary Entry not found")));
    }
}
