package com.burak.controller;


import com.burak.dto.request.AuthLoginRequestDto;
import com.burak.dto.request.AuthRegisterRequestDto;
import com.burak.dto.response.AuthLoginResponseDto;
import com.burak.repository.entity.Auth;
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
    @Operation(summary = "kullanıcı kayıt eden metod") 
    public ResponseEntity<Auth> register(@RequestBody @Valid AuthRegisterRequestDto authRegisterRequestDto){

        return ResponseEntity.ok(authService.register(authRegisterRequestDto));

    }

    @PostMapping(DOLOGIN)
    public ResponseEntity<AuthLoginResponseDto> dologin(@RequestBody @Valid AuthLoginRequestDto authLoginRequestDto){

        return ResponseEntity.ok(authService.dologin(authLoginRequestDto).get());

    }
}
