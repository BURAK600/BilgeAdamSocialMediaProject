package com.burak.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserUpdateRequestDto {

    private String userName;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String photo;
    private String address;
    private String about;
    @NotNull
    private String token;

}
