package com.burak.repository.entity;

import com.burak.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tblauth")
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PENDING;
}
