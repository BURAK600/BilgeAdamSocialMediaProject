package com.burak.controller;

import com.burak.dto.request.ActivatedRequestDto;
import com.burak.dto.request.UserCreateRequestDto;
import com.burak.dto.request.UserUpdateRequestDto;
import com.burak.exception.ErrorType;
import com.burak.exception.UserProfileServiceException;
import com.burak.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.burak.constants.ApiUrls.SAVE;
import static com.burak.constants.ApiUrls.USERPROFILE;

@RestController
@RequiredArgsConstructor
@RequestMapping(USERPROFILE)
public class UserProfilController {

   private final UserProfileService userProfileService;

   @PostMapping(SAVE)
   public ResponseEntity<Boolean> save(@RequestBody UserCreateRequestDto userCreateRequestDto){
      try {
      userProfileService.save(userCreateRequestDto);
         return ResponseEntity.ok(true);
      }catch (UserProfileServiceException u){
         throw new UserProfileServiceException(ErrorType.USER_NOT_CREATED);
      }

   }

   @PostMapping("/activate")
   public ResponseEntity<Boolean> activateStatus(@RequestBody ActivatedRequestDto activatedRequestDto){


      return ResponseEntity.ok(userProfileService.activateStatus(activatedRequestDto));
   }

   @PostMapping("/update")
   public ResponseEntity<Boolean> updateProfile(@RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto){
      return ResponseEntity.ok(userProfileService.updateUser(userUpdateRequestDto));
   }


}
