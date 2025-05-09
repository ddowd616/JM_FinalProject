package mil.swf.jm_finalproject.controller;

import mil.swf.jm_finalproject.DTO.UserInfoDTO;
import mil.swf.jm_finalproject.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userInfo")
public class UserInfoController {
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserInfoDTO> createUserInfo(@RequestBody UserInfoDTO userInfoDTO){
        return ResponseEntity.ok(userInfoService.createUserInfo(userInfoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDTO> getUserInfoById(@PathVariable Long id){
        return userInfoService.getUserInfoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}