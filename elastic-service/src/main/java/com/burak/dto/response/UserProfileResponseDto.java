package com.burak.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserProfileResponseDto {

    private String id;
    private Long authId;
    private String userName;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String photo;
    private String address;
    private String about;
    private Long created;
    private Long updated;

}
