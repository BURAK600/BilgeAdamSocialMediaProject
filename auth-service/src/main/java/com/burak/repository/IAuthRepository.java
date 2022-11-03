package com.burak.repository;


import com.burak.dto.request.AuthLoginRequestDto;
import com.burak.repository.entity.Auth;
import com.burak.repository.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth, Long> {


    @Query("select count(a.userName)>0 from Auth as a where a.userName=?1")
    Boolean existUserName(String userName);

    Optional<Auth> findOptionalByUserNameAndPassword(String userName, String password);

    List<Auth> findAllByRole(Roles role);
     @Query("select a from Auth a where a.status='ACTIVE' or a.status = 'PENDING'")
    Optional<List<Auth>> findAllActiveAndPendingAuth();
}
