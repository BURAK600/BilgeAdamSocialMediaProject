package com.burak.repository.entity;

import com.burak.repository.enums.Roles;
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
    private String userName;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    Roles role= Roles.USER;

}
