package com.burak.repository.entity;

import com.burak.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class UserProfile {
@Id
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
    @Builder.Default
    private Long created = System.currentTimeMillis();
    private Long updated;


    @Builder.Default
    private Status status = Status.PENDING;
}
