package com.burak.repository;

import com.burak.repository.entity.UserProfile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;


public interface IUserProfileRepository extends ElasticsearchRepository<UserProfile, String> {

    Optional<UserProfile> findOptionalByAuthId(Long authId);


    List<UserProfile> findByUserNameContainingIgnoreCase(String userName);
}
