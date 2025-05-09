package mil.swf.jm_finalproject.service;

import mil.swf.jm_finalproject.DTO.UserInfoDTO;
import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.entity.UserInfo;
import mil.swf.jm_finalproject.repository.CountryRepository;
import mil.swf.jm_finalproject.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final CountryRepository countryRepository;

    public UserInfoService(UserInfoRepository userInfoRepository, CountryRepository countryRepository) {
        this.userInfoRepository = userInfoRepository;
        this.countryRepository = countryRepository;
    }

    public UserInfoDTO createUserInfo(UserInfoDTO dto){
        UserInfoDTO ReqRes = new UserInfoDTO();
        Country country = countryRepository.findById(dto.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        UserInfo userInfo = new UserInfo(
                country,
                dto.getUserName(),
                dto.getUserPassword(),
                dto.getDateOfBirth()
        );

        UserInfo saved = userInfoRepository.save(userInfo);

        // Setup return
        ReqRes.setUserId(saved.getUserId());
        ReqRes.setUserName(saved.getUserName());
        ReqRes.setUserPassword(saved.getUserPassword());
        ReqRes.setCountryId(saved.getCountryOfOrigin().getCountryId());
        ReqRes.setDateOfBirth(saved.getDateOfBirth());

        return ReqRes;

    }

    public Optional<UserInfoDTO> getUserInfoById(Long id) {
        Optional<UserInfo> userInfo = userInfoRepository.findById(id);
        return userInfo.map(u -> new UserInfoDTO(
                u.getUserId(),
                u.getCountryOfOrigin().getCountryId(),
                u.getUserName(),
                u.getUserPassword(),
                u.getDateOfBirth()
        ));
    }

    public UserInfoDTO updateUserInfo(Long userId, UserInfoDTO dto) {
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        Country country = countryRepository.findById(dto.getCountryId())
                .orElseThrow(()-> new RuntimeException("Country not found"));

        userInfo.setCountryOfOrigin(country);
        userInfo.setUserName(dto.getUserName());
        userInfo.setUserPassword(dto.getUserPassword());
        userInfo.setDateOfBirth(dto.getDateOfBirth());

        UserInfo updatedUser = userInfoRepository.save(userInfo);

        return new UserInfoDTO(
                updatedUser.getUserId(),
                updatedUser.getCountryOfOrigin().getCountryId(),
                updatedUser.getUserName(),
                updatedUser.getUserPassword(),
                updatedUser.getDateOfBirth()
        );
    }
}
