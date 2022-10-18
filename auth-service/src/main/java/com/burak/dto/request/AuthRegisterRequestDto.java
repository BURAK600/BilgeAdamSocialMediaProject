package com.burak.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthRegisterRequestDto {
    @NotBlank
    @Size(min = 8, max = 64)
    private String userName;
    @NotNull(message = "Şifre boş bırakılamaz!!!")
    @Size(min = 8, max = 64)
    private String password;
    @NotBlank
    @Email(message = "Email doğru giriniz!!!")
    private String email;
    private String adminCode;
}
