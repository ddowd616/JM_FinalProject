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


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


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
}
