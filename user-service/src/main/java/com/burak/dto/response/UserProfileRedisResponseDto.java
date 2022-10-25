package com.burak.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserProfileRedisResponseDto {

    private String userName;
    private String name;
    private String email;
    private String phone;
    private String photo;
    private String address;
    private String about;
    private String status;
}
