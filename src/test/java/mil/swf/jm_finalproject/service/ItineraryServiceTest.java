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

    @Test
    void testGetItineraryEntryById_Found(){
    UserInfo userInfo = new UserInfo();
    userInfo.setUserId(89L);

    Country country = new Country();
    country.setCountryId(56L);

    Itinerary itinerary = new Itinerary(
            userInfo,
            country,
            4,
            true,
            LocalDate.of(2019,3,12),
            LocalDate.of(2019,6,12),
            90,
            true
    );
    itinerary.setId(20L);

    when(itineraryRepository.findById(20L)).thenReturn(Optional.of(itinerary));

    ItineraryDTO result = itineraryService.getItineraryEntryById(20L);

    assertNotNull(result);
    assertEquals(20L,result.getId());
    assertEquals(89L,result.getUserId());
    assertEquals(56L,result.getCountryId());
    assertEquals(4,result.getOrderOnTrip());
    assertTrue(result.getUserWantsCurrencyExchangeRate());

    }

    @Test
    void testGetItineraryEntryById_NotFound(){
        when(itineraryRepository.findById(300L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,()->{itineraryService.getItineraryEntryById(300L);});

        assertEquals("Itinerary Entry not found",exception.getMessage());
    }

    @Test
    void testUpdateItineraryEntry_Found(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(4L);

        Country country = new Country();
        country.setCountryId(45L);

        Itinerary existingEntry = new Itinerary();
        existingEntry.setId(42L);
        existingEntry.setUserInfo(userInfo);
        existingEntry.setCountry(country);

        ItineraryDTO updateDTO = new ItineraryDTO();
        updateDTO.setId(42L);
        updateDTO.setUserId(4L);
        updateDTO.setCountryId(45L);
        updateDTO.setOrderOnTrip(4);
        updateDTO.setCountryOfOrigin(true);
        updateDTO.setStartDate(LocalDate.of(2022,7,15));
        updateDTO.setEndDate(LocalDate.of(2022,7,24));
        updateDTO.setDaysSpentInCountry(9);
        updateDTO.setUserWantsCurrencyExchangeRate(true);

        when(itineraryRepository.findById(42L)).thenReturn(Optional.of(existingEntry));
        when(userInfoRepository.findById(4L)).thenReturn(Optional.of(userInfo));
        when(countryRepository.findById(45L)).thenReturn(Optional.of(country));
        when(itineraryRepository.save(any(Itinerary.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ItineraryDTO result = itineraryService.updateItineraryEntry(42L,updateDTO);

        assertEquals(4L,result.getUserId());
        assertEquals(45L,result.getCountryId());
        assertTrue(result.getCountryOfOrigin());
        assertTrue(result.getUserWantsCurrencyExchangeRate());
        assertEquals(4,result.getOrderOnTrip());
        assertEquals(LocalDate.of(2022,7,15),result.getStartDate());
        assertEquals(LocalDate.of(2022,7,24),result.getEndDate());
        assertEquals(9,result.getDaysSpentInCountry());
    }

    @Test
    void testUpdateItineraryEntry_NotFound(){
        when(itineraryRepository.findById(4L)).thenReturn(Optional.empty());

        ItineraryDTO updateDTO = new ItineraryDTO();
        updateDTO.setUserId(3L);
        updateDTO.setCountryId(35L);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> itineraryService.updateItineraryEntry(4L,updateDTO));

        assertEquals("Itinerary Entry not found",ex.getMessage());
    }

    @Test
    void testDeleteItineraryEntry_Success(){
        Long itineraryId = 46L;
        Itinerary mockItinerary = new Itinerary();
        mockItinerary.setId(itineraryId);

        when(itineraryRepository.findById(itineraryId)).thenReturn(Optional.of(mockItinerary));

        itineraryService.deleteItineraryEntry(itineraryId);

        verify(itineraryRepository).delete(mockItinerary);


    }

    @Test
    void testDeleteItineraryEntry_NotFound(){
        Long itineraryId = 999L;
        when(itineraryRepository.findById(itineraryId)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,() -> itineraryService.deleteItineraryEntry(itineraryId));

        assertEquals("Itinerary Entry not found",ex.getMessage());
        verify(itineraryRepository,never()).delete(any());

    }

}
