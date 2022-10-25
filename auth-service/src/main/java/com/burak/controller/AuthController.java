package com.burak.controller;


import com.burak.dto.request.ActivatedRequestDto;
import com.burak.dto.request.AuthLoginRequestDto;
import com.burak.dto.request.AuthRegisterRequestDto;
import com.burak.dto.response.AuthLoginResponseDto;
import com.burak.dto.response.AuthRegisterResponseDto;
import com.burak.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
