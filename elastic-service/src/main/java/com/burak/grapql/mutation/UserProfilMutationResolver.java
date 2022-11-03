package com.burak.grapql.mutation;

import com.burak.grapql.model.UserProfileInput;
import com.burak.mapper.IUserProfileMapper;
import com.burak.service.UserProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Component
public class UserProfilMutationResolver implements GraphQLMutationResolver {

    private final UserProfileService userProfileService;

    public Boolean createUser(UserProfileInput userProfileInput){
        userProfileService.save(IUserProfileMapper.INSTANSE.toUserProfile(userProfileInput));
        return true;
    }
}
