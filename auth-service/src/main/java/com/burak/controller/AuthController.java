package com.burak.controller;


import com.burak.dto.request.ActivatedRequestDto;
import com.burak.dto.request.AuthLoginRequestDto;
import com.burak.dto.request.AuthRegisterRequestDto;
import com.burak.dto.response.ActivePendingUserResponseDto;
import com.burak.dto.response.AuthLoginResponseDto;
import com.burak.dto.response.AuthRegisterResponseDto;
import com.burak.dto.response.RoleResponseDto;
import com.burak.repository.entity.Auth;
import com.burak.repository.enums.Roles;
import com.burak.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.burak.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {

    private final AuthService authService;


    @PostMapping(REGISTER)
    @Operation(summary = "Kullanıcı kayıt metodu")
    public ResponseEntity<AuthRegisterResponseDto> register(@RequestBody @Valid AuthRegisterRequestDto authRegisterRequestDto){

        return ResponseEntity.ok(authService.register(authRegisterRequestDto));

    }

    @PostMapping(DOLOGIN)
    @Operation(summary = "Kullanıcı giriş metodu")
    public ResponseEntity<AuthLoginResponseDto> dologin(@RequestBody @Valid AuthLoginRequestDto authLoginRequestDto){

        return ResponseEntity.ok(authService.dologin(authLoginRequestDto).get());

    }
    @PostMapping("activate")
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivatedRequestDto activatedRequestDto){

       return ResponseEntity.ok(authService.activatedStatus(activatedRequestDto));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello!!!");
    }

    @GetMapping("reddisgetupper")
    public ResponseEntity<String> getUpperCase(String name){

        return ResponseEntity.ok(authService.getUpperCase(name));
    }


    @GetMapping("/findbyrole")
    public ResponseEntity<List<RoleResponseDto>> findRoles(String roles){

        return ResponseEntity.ok(authService.findByRole(roles));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAuth(@RequestBody String token){
        return ResponseEntity.ok(authService.delete(token));


    }

    @GetMapping("/getactivependingusers")
    public ResponseEntity<List<ActivePendingUserResponseDto>> getActivePendingUsers(){
        return ResponseEntity.ok(authService.getActivePendingUsers());
    }
}
