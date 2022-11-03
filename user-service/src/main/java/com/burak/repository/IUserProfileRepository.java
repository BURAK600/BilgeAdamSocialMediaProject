package com.burak.repository;



import com.burak.repository.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends MongoRepository<UserProfile, String> {

    Optional<UserProfile> findOptinalByAuthId(Long id);
    Optional<UserProfile> findOptinalByUserName(String userName);

    Optional<UserProfile> findOptinalByUserNameEqualsIgnoreCase(String userName);


    @Query("select u from UserProfile as u where u.status='ACTIVE'")
    List<UserProfile> getActiveProfile();


    Optional<UserProfile> findOptionalByAuthId(Long id);
}
