package mil.swf.jm_finalproject.service;

import mil.swf.jm_finalproject.DTO.UserInfoDTO;
import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.entity.UserInfo;
import mil.swf.jm_finalproject.repository.CountryRepository;
import mil.swf.jm_finalproject.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final CountryRepository countryRepository;

    public UserInfoService(UserInfoRepository userInfoRepository, CountryRepository countryRepository) {
        this.userInfoRepository = userInfoRepository;
        this.countryRepository = countryRepository;
    }

    public UserInfoDTO createUserInfo(UserInfoDTO dto){
       Country country = countryRepository.findById(dto.getCountryId())
               .orElseThrow(() -> new RuntimeException("Country not found"));

       UserInfo userInfo = new UserInfo(
               country,
               dto.getUserName(),
               dto.getUserPassword(),
               dto.getDateOfBirth()
       );

       UserInfo saved = userInfoRepository.save(userInfo);

       return new UserInfoDTO(
               saved.getUserId(),
               saved.getCountryOfOrigin().getCountryId(),
               saved.getUserName(),
               saved.getUserPassword(),
               saved.getDateOfBirth()
       );
    }
}
