package com.burak.service;

import com.burak.repository.IUserProfileRepository;
import com.burak.repository.entity.UserProfile;
import com.burak.repository.enums.Status;
import com.burak.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserProfileService extends ServiceManager<UserProfile, String> {

    private final IUserProfileRepository repository;
    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;

    }



}
