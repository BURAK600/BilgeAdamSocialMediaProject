package com.burak.repository;


import com.burak.dto.request.AuthLoginRequestDto;
import com.burak.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth, Long> {


    @Query("select count(a.userName)>0 from Auth as a where a.userName=?1")
    Boolean existUserName(String userName);

    Optional<Auth> findOptionalByUserNameAndPassword(String userName, String password);
}
