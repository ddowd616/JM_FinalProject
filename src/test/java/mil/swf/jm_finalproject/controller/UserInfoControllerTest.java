package mil.swf.jm_finalproject.controller;

import mil.swf.jm_finalproject.DTO.UserInfoDTO;
import mil.swf.jm_finalproject.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.entity.CurrencyCode;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

    @Test
    void testGetUserInfoById_Found() throws Exception {
        var userDTO = new UserInfoDTO(4L,2L,"ddowd","123#@",LocalDate.of(2018,11,23));
        when(userInfoService.getUserInfoById(4L)).thenReturn(Optional.of(userDTO));

        mockMvc.perform(get("/api/userInfo/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(4))
                .andExpect(jsonPath("$.countryId").value(2))
                .andExpect(jsonPath("$.userName").value("ddowd"))
                .andExpect(jsonPath("$.userPassword").value("123#@"))
                .andExpect(jsonPath("$.dateOfBirth").value("2018-11-23"));
    }

    @Test
    void testGetUserInfoById_NotFound() throws Exception {
        when(userInfoService.getUserInfoById(300L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/userInfo/300"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }

    @Test
    void testUpdateUserInfo_Success() throws Exception {
        Long userId = 9L;

        UserInfoDTO updateDTO = new UserInfoDTO(userId,1L,"updatedName","updatedPass123",LocalDate.of(2001,11,11));

        when(userInfoService.updateUserInfo(eq(userId),any(UserInfoDTO.class))).thenReturn(updateDTO);

        mockMvc.perform(put("/api/userInfo/{id}",userId)
                .contentType("application/json")
                .content("""
                            {
                              "countryId": 1,
                              "userName": "updatedName",
                              "userPassword": "updatedPass123",
                              "dateOfBirth": "2001-11-11"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(9))
                .andExpect(jsonPath("$.countryId").value(1))
                .andExpect(jsonPath("$.userName").value("updatedName"))
                .andExpect(jsonPath("$.userPassword").value("updatedPass123"))
                .andExpect(jsonPath("$.dateOfBirth").value("2001-11-11"));

    }

    @Test
    void testUpdateUserInfo_NotFound() throws Exception {
        Long userIdLookingFor = 99L;

        UserInfoDTO updateDTO = new UserInfoDTO(25L,1L,"updatedName","updatedPass123",LocalDate.of(2001,11,11));

        when(userInfoService.updateUserInfo(eq(userIdLookingFor),any(UserInfoDTO.class))).thenThrow(new RuntimeException("User not found"));

        mockMvc.perform(put("/api/userInfo/{id}",userIdLookingFor)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                              "countryId": 1,
                              "userName": "updatedName",
                              "userPassword": "updatedPass123",
                              "dateOfBirth": "2001-11-11"
                            }
                        """))
                .andExpect(status().isNotFound());

    }
}
