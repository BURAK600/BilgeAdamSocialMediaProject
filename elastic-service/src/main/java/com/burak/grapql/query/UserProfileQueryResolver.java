package com.burak.grapql.query;

import com.burak.repository.IUserProfileRepository;
import com.burak.repository.entity.UserProfile;
import com.burak.service.UserProfileService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserProfileQueryResolver implements GraphQLQueryResolver {

    private final IUserProfileRepository iUserProfileRepository;
    private final UserProfileService userProfileService;

    public List<UserProfile> findByUserName(String userName){
        return iUserProfileRepository.findByUserNameContainingIgnoreCase(userName);
    }

    public Iterable<UserProfile> findAll(){
        return userProfileService.findAll();
    }

}
