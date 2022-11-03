package com.burak.grapql.model;

import lombok.Data;

@Data
public class UserProfileInput {

    private Long authId;
    private String userName;
    private String email;

}
