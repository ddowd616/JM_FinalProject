package mil.swf.jm_finalproject.service;

import mil.swf.jm_finalproject.DTO.UserInfoDTO;
import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.entity.CurrencyCode;
import mil.swf.jm_finalproject.entity.UserInfo;
import mil.swf.jm_finalproject.repository.CountryRepository;
import mil.swf.jm_finalproject.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.time.LocalDate;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


public class UserInfoServiceTest {

    private UserInfoRepository userInfoRepository;
    private CountryRepository countryRepository;
    private UserInfoService userInfoService;

    @BeforeEach
    void setUp(){
        userInfoRepository = Mockito.mock(UserInfoRepository.class);
        countryRepository = Mockito.mock(CountryRepository.class);
        userInfoService = new UserInfoService(userInfoRepository,countryRepository);
    }

    @Test
    void testCreateUserInfo (){
        CurrencyCode usd = new CurrencyCode("US Dollar", "USD");
        Country country = new Country(usd,"United States","US Dollar");
        country.setCountryId(1L);

        when(countryRepository.findById(1L)).thenReturn(Optional.of(country));

        UserInfo savedEntity = new UserInfo(country,"ddowd", "123@#!3d",LocalDate.of(2018,10,10));
        savedEntity.setUserId(100L);

        when(userInfoRepository.save(any(UserInfo.class))).thenReturn(savedEntity);

        UserInfoDTO dto = new UserInfoDTO(null, 1L, "ddowd","123@#!3d",LocalDate.of(2018,10,10));

        UserInfoDTO savedUser = userInfoService.createUserInfo(dto);

        assertNotNull(savedUser.getUserId());
        assertEquals("ddowd",savedUser.getUserName());
        assertEquals(1L,savedUser.getCountryId());
    }

    @Test
    void testGetUserInfoById_Found(){
        CurrencyCode usd = new CurrencyCode("US Dollar","USD");
        Country usa = new Country(usd, "United States", "US Dollar");
        UserInfo userInfo = new UserInfo(usa,"ddowd","123@#",LocalDate.of(2018,10,10));
        userInfo.setUserId(1L);

        when(userInfoRepository.findById(1L)).thenReturn(Optional.of(userInfo));

        var result = userInfoService.getUserInfoById(1L);

        assertTrue(result.isPresent());
        var dto = result.get();
        assertEquals("ddowd",dto.getUserName());
        assertEquals("123@#",dto.getUserPassword());
    }

    @Test
    void testGetUserInfoById_NotFound(){
        when(userInfoRepository.findById(400L)).thenReturn(Optional.empty());

        var result = userInfoService.getUserInfoById(400L);

        assertTrue(result.isEmpty());
    }

    @Test
    void testUpdateUserInfoWithId_Success(){
        Long userId = 65L;
        Long countryId = 3L;

        CurrencyCode eur = new CurrencyCode("Euro", "EUR");
        Country fra = new Country(eur, "France", "Euro");
        fra.setCountryId(countryId);

        UserInfo existingUser = new UserInfo(fra,"mana12","34543@#",LocalDate.of(1992,12,26));
        existingUser.setUserId(userId);

        UserInfoDTO updateDTO = new UserInfoDTO(userId,countryId,"newName","newPass",LocalDate.of(2000,11,14));

        when(userInfoRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(countryRepository.findById(countryId)).thenReturn(Optional.of(fra));
        when(userInfoRepository.save(any(UserInfo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserInfoDTO result = userInfoService.updateUserInfo(userId, updateDTO);

        assertEquals("newName",result.getUserName());
        assertEquals("newPass",result.getUserPassword());
        assertEquals(LocalDate.of(2000,11,14),result.getDateOfBirth());
        assertEquals(countryId,result.getCountryId());

    }

    @Test
    void testUpdateUserInfo_UserNotFound(){
        Long userId = 99L;
        UserInfoDTO updateUserInfoDTO = new UserInfoDTO(userId,1L,"anyUser","pass",LocalDate.of(2012,9,12));

        when(userInfoRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,() -> userInfoService.updateUserInfo(userId,updateUserInfoDTO));
    }

    @Test
    void testUpdateUserInfo_CountryNotFound(){
        Long userId = 3L;
        Long countryId =10L;

        CurrencyCode eur = new CurrencyCode("Euro","EUR");
        Country ger = new Country(eur,"Germany", "Euro");
        ger.setCountryId(8L);

        UserInfo existingUser = new UserInfo(ger,"user","pass",LocalDate.of(2011,10,28));
        existingUser.setUserId(userId);

        UserInfoDTO updateDTO = new UserInfoDTO(userId,countryId,"user","pass",LocalDate.of(2019,6,16));

        when(userInfoRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(countryRepository.findById(countryId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->userInfoService.updateUserInfo(userId,updateDTO));
    }

    @Test
    void testDeleteUser_Found(){
        Long userId = 1L;

        when(userInfoRepository.existsById(userId)).thenReturn(true);

        userInfoService.deleteUserInfo(userId);

        verify(userInfoRepository).deleteById(userId);
    }

    @Test
    void testDeleteUser_NotFound(){
        Long userId = 99L;

        when(userInfoRepository.existsById(userId)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userInfoService.deleteUserInfo(userId);
        });

        assertEquals("UserInfo with id 99 not found", exception.getMessage());
        verify(userInfoRepository, never()).deleteById(anyLong());
    }

}
