package mil.swf.jm_finalproject.DTO;

import mil.swf.jm_finalproject.entity.Country;
import mil.swf.jm_finalproject.entity.UserInfo;

public class UserInfoMapper {

    public static UserInfoDTO toDTO (UserInfo userInfo){
        return new UserInfoDTO(
                userInfo.getUserId(),
                userInfo.getCountryOfOrigin().getCountryId(),
                userInfo.getUserName(),
                userInfo.getUserPassword(),
                userInfo.getDateOfBirth());
    }

    public static UserInfo toEntity(UserInfoDTO userInfoDTO, Country country){
        UserInfo entity = new UserInfo();
        entity.setUserId(userInfoDTO.getUserId());
        entity.setCountryOfOrigin(country);
        entity.setUserName(userInfoDTO.getUserName());
        entity.setUserPassword(userInfoDTO.getUserPassword());
        entity.setDateOfBirth(userInfoDTO.getDateOfBirth());
        return entity;
    }
}
