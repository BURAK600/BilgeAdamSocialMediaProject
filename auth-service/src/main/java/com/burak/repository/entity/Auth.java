package com.burak.repository.entity;

import com.burak.repository.enums.Roles;
import com.burak.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="tblauth")
@Entity
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String userName;
    private String password;
    private String email;

    private String activatedCode;
    private String adminCode;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Roles role= Roles.USER;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Status status= Status.PENDING;



}
