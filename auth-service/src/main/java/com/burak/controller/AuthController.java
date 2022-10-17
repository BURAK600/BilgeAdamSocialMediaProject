package com.burak.controller;


import com.burak.dto.request.AuthRegisterRequestDto;
import com.burak.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.burak.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {

    private final AuthService authService;

    @GetMapping(REGISTER)
    public ResponseEntity<String> register(AuthRegisterRequestDto authRegisterRequestDto){

        return ResponseEntity.ok("kayıt başarılı");

    }
}
