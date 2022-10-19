package com.burak;


import com.burak.dto.request.UserCreateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.burak.constants.ApiUrls.SAVE;

@FeignClient(name = "user-profile-service", url = "http://localhost:8092/api/v1/userprofile", decode404 = true)
public interface IUserProfileManager {

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserCreateRequestDto userCreateRequestDto);
}
