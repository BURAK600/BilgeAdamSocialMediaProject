package com.burak.controller;


import com.burak.dto.response.UserProfileResponseDto;
import com.burak.mapper.IUserProfileMapper;
import com.burak.repository.entity.UserProfile;
import com.burak.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.burak.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ELASTIC)
public class UserProfileController {

    private final UserProfileService userProfileService;


    @GetMapping(GETALL)
    public ResponseEntity<Iterable<UserProfile>> getAll(){

        return ResponseEntity.ok(userProfileService.findAll());
    }

    @PostMapping(SAVE)
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfileResponseDto userProfile){
        return ResponseEntity.ok(userProfileService.save(IUserProfileMapper.INSTANSE.toUserProfile(userProfile)));
    }


    @PostMapping(UPDATE)
    public ResponseEntity<UserProfile> updateUser(@RequestBody  UserProfileResponseDto userProfile){
        return ResponseEntity.ok(userProfileService.save(IUserProfileMapper.INSTANSE.toUserProfile(userProfile)));
    }


//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Boolean> deleteUser( @PathVariable Long id){
//        return ResponseEntity.ok(userProfileService.deleteUser(id));
//    }
}
