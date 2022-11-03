package com.burak.utility;

import com.burak.controller.UserProfileController;
import com.burak.dto.response.UserProfileResponseDto;
import com.burak.manager.IUserProfileManager;
import com.burak.mapper.IUserProfileMapper;
import com.burak.repository.entity.UserProfile;
import com.burak.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserComponent {

    private final IUserProfileManager iUserProfileManager;
    private final UserProfileService userProfileService;

    @PostConstruct
    public void firstRun(){
        List<UserProfileResponseDto> userProfiles = iUserProfileManager.userList().getBody();
        userProfileService.saveAll(userProfiles.stream().map(userProfile->
                IUserProfileMapper.INSTANSE.toUserProfile(userProfile)).collect(Collectors.toList()));

    }
}
