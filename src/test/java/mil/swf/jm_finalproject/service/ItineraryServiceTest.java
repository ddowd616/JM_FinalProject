package mil.swf.jm_finalproject.service;

import mil.swf.jm_finalproject.DTO.ItineraryDTO;
import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.entity.Itinerary;
import mil.swf.jm_finalproject.entity.UserInfo;
import mil.swf.jm_finalproject.repository.CountryRepository;
import mil.swf.jm_finalproject.repository.ItineraryRepository;
import mil.swf.jm_finalproject.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItineraryServiceTest {
    private ItineraryRepository itineraryRepository;
    private UserInfoRepository userInfoRepository;
    private CountryRepository countryRepository;

    private ItineraryService itineraryService;

    @BeforeEach
    void setUp(){
        itineraryRepository = Mockito.mock(ItineraryRepository.class);
        userInfoRepository = Mockito.mock(UserInfoRepository.class);
        countryRepository = Mockito.mock(CountryRepository.class);
        itineraryService = new ItineraryService(itineraryRepository,userInfoRepository,countryRepository);

    }

    @Test
    void testCreateItineraryEntry(){

        ItineraryDTO dto = new ItineraryDTO(
                null,
                5L,
                6L,
                5,
                false,
                LocalDate.of(2024,4,1),
                LocalDate.of(2024,5,1),
                4,
                true
        );

        UserInfo mockUser = new UserInfo();
        mockUser.setUserId(5L);

        Country mockCountry = new Country();
        mockCountry.setCountryId(6L);

        when(userInfoRepository.findById(5L)).thenReturn(Optional.of(mockUser));
        when(countryRepository.findById(6L)).thenReturn(Optional.of(mockCountry));

        Itinerary saveItineraryEntry = new Itinerary(mockUser,mockCountry,5,false,LocalDate.of(2024,4,1),LocalDate.of(2024,5,1),4,true);
        saveItineraryEntry.setId(30L);

        when(itineraryRepository.save(any(Itinerary.class))).thenReturn(saveItineraryEntry);

        ItineraryDTO result = itineraryService.createItineraryEntry(dto);

        assertNotNull(result);
        assertEquals(30L,result.getId());
        assertEquals(mockUser.getUserId(),result.getUserId());
        assertEquals(mockCountry.getCountryId(),result.getCountryId());
        assertEquals(4, result.getDaysSpentInCountry());

        verify(itineraryRepository,times(1)).save(any(Itinerary.class));

    }

}
