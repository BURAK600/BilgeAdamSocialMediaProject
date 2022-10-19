package com.burak.service;

import com.burak.dto.request.UserCreateRequestDto;
import com.burak.mapper.IUserProfileMapper;
import com.burak.repository.IUserProfileRepository;
import com.burak.repository.entity.UserProfile;
import com.burak.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile, Long> {

    private final IUserProfileRepository iUserProfileRepository;

    public UserProfileService(IUserProfileRepository iUserProfileRepository) {
        super(iUserProfileRepository);
        this.iUserProfileRepository = iUserProfileRepository;
    }

    public UserProfile save(UserCreateRequestDto userCreateRequestDto) {
        UserProfile userProfile = IUserProfileMapper.INSTANSE.toUserProfile(userCreateRequestDto);

        return iUserProfileRepository.save(userProfile);


    }
}
