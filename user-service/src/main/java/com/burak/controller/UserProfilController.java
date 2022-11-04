package com.burak.controller;

import com.burak.dto.request.ActivatedRequestDto;
import com.burak.dto.request.UserCreateRequestDto;
import com.burak.dto.request.UserUpdateRequestDto;
import com.burak.dto.response.RoleResponseDto;
import com.burak.dto.response.UserProfileRedisResponseDto;
import com.burak.dto.response.UserProfileResponseDto;
import com.burak.exception.ErrorType;
import com.burak.exception.UserProfileServiceException;
import com.burak.mapper.IUserProfileMapper;
import com.burak.repository.entity.UserProfile;
import com.burak.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.burak.constants.ApiUrls.*;

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

   @GetMapping("/findall")
   public ResponseEntity<List<UserProfileResponseDto>> findAll(){

      return ResponseEntity.ok(IUserProfileMapper.INSTANSE.toUserProfileResponseDto(userProfileService.findAll()));
   }


   @PostMapping("/activate")
   public ResponseEntity<Boolean> activateStatus(@RequestBody ActivatedRequestDto activatedRequestDto){


      return ResponseEntity.ok(userProfileService.activateStatus(activatedRequestDto));
   }

   @PostMapping("/update")
   public ResponseEntity<Boolean> updateProfile(@RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto){
      return ResponseEntity.ok(userProfileService.updateUserForRedis(userUpdateRequestDto));
   }

   @PostMapping("/updateredis")
   public ResponseEntity<Boolean> updateProfileForRedis(@RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto){
      return ResponseEntity.ok(userProfileService.updateUserForRedis(userUpdateRequestDto));
   }
@PutMapping("/updatewithrabbitmq")
   public ResponseEntity<Boolean> updateProfileWithRabbitMQ(@RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto){
      return ResponseEntity.ok(userProfileService.updateUserWithRabbitMQ(userUpdateRequestDto));
   }

   @GetMapping("/findbyusername/{userName}")
   public ResponseEntity<UserProfileRedisResponseDto> findByUserName(@PathVariable String userName){
      return ResponseEntity.ok(userProfileService.findByUserName(userName));
   }

   @GetMapping("/findallactiveprofile")

   public ResponseEntity<List<UserProfile>> findAllActiveProfile(){

      return ResponseEntity.ok(userProfileService.findAllActiveProfile());
   }


   @GetMapping("/findbyrole")
   public ResponseEntity<List<RoleResponseDto>> findAllByRole(String roles){
      return ResponseEntity.ok(userProfileService.findByRole(roles));
   }


   @GetMapping(USERPROFILE_LIST)
   public ResponseEntity<List<UserProfile>> userList(){
      List<UserProfile> list = userProfileService.findAll();
      return ResponseEntity.ok(list);
   }


   @GetMapping("/findbypagable")
   public ResponseEntity<Page<UserProfile>> findAllPage(int pageSize, int pageNumber, String direction, String parameter){
      return ResponseEntity.ok(userProfileService.findAllPage(pageSize, pageNumber,direction,parameter));
   }






}
