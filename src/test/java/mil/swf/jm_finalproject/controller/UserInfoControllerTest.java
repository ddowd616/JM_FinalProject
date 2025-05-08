package mil.swf.jm_finalproject.controller;

import mil.swf.jm_finalproject.DTO.UserInfoDTO;
import mil.swf.jm_finalproject.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.entity.CurrencyCode;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserInfoController.class)
public class UserInfoControllerTest {

    @MockitoBean
    private UserInfoService userInfoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateUserInfo() throws Exception {
        CurrencyCode usd = new CurrencyCode("US Dollar", "USD");
        Country usa = new Country(usd, "United States", "US Dollar");
        usa.setCountryId(1L);


        UserInfoDTO savedUserDTO = new UserInfoDTO(1L,usa.getCountryId(),"ddowd","123@!3",LocalDate.of(2018,10,10));

        when(userInfoService.createUserInfo(any(UserInfoDTO.class))).thenReturn(savedUserDTO);

        mockMvc.perform(post("/api/userInfo/create")
                        .contentType("application/json")
                        .content("{\"countryId\":1, \"userName\":\"ddowd\", \"userPassword\":\"123@!3\", \"dateOfBirth\":\"2018-10-10\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.countryId").value(1))
                .andExpect(jsonPath("$.userName").value("ddowd"));
    }
}
