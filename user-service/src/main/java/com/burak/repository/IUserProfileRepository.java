package com.burak.repository;



import com.burak.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findOptinalByAuthId(Long id);
    Optional<UserProfile> findOptinalByUserName(String userName);

    Optional<UserProfile> findOptinalByUserNameEqualsIgnoreCase(String userName);


    @Query("select u from UserProfile as u where u.status='ACTIVE'")
    List<UserProfile> getActiveProfile();


    Optional<List<UserProfile>> findAllOptionalByRole(String role);
}
