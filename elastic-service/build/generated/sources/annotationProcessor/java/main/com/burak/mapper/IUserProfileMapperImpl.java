package com.burak.mapper;

import com.burak.dto.response.UserProfileResponseDto;
import com.burak.grapql.model.UserProfileInput;
import com.burak.repository.entity.UserProfile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-02T12:00:59+0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class IUserProfileMapperImpl implements IUserProfileMapper {

    @Override
    public UserProfile toUserProfile(UserProfileResponseDto userProfileResponseDto) {
        if ( userProfileResponseDto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder userProfile = UserProfile.builder();

        userProfile.id( userProfileResponseDto.getId() );
        userProfile.authId( userProfileResponseDto.getAuthId() );
        userProfile.userName( userProfileResponseDto.getUserName() );
        userProfile.password( userProfileResponseDto.getPassword() );
        userProfile.name( userProfileResponseDto.getName() );
        userProfile.email( userProfileResponseDto.getEmail() );
        userProfile.phone( userProfileResponseDto.getPhone() );
        userProfile.photo( userProfileResponseDto.getPhoto() );
        userProfile.address( userProfileResponseDto.getAddress() );
        userProfile.about( userProfileResponseDto.getAbout() );
        userProfile.created( userProfileResponseDto.getCreated() );
        userProfile.updated( userProfileResponseDto.getUpdated() );

        return userProfile.build();
    }

    @Override
    public List<UserProfileResponseDto> toUserProfileResponseDto(List<UserProfile> userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        List<UserProfileResponseDto> list = new ArrayList<UserProfileResponseDto>( userProfile.size() );
        for ( UserProfile userProfile1 : userProfile ) {
            list.add( userProfileToUserProfileResponseDto( userProfile1 ) );
        }

        return list;
    }

    @Override
    public UserProfile toUserProfile(UserProfileInput userProfileInput) {
        if ( userProfileInput == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder userProfile = UserProfile.builder();

        userProfile.authId( userProfileInput.getAuthId() );
        userProfile.userName( userProfileInput.getUserName() );
        userProfile.email( userProfileInput.getEmail() );

        return userProfile.build();
    }

    protected UserProfileResponseDto userProfileToUserProfileResponseDto(UserProfile userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        UserProfileResponseDto.UserProfileResponseDtoBuilder userProfileResponseDto = UserProfileResponseDto.builder();

        userProfileResponseDto.id( userProfile.getId() );
        userProfileResponseDto.authId( userProfile.getAuthId() );
        userProfileResponseDto.userName( userProfile.getUserName() );
        userProfileResponseDto.password( userProfile.getPassword() );
        userProfileResponseDto.name( userProfile.getName() );
        userProfileResponseDto.email( userProfile.getEmail() );
        userProfileResponseDto.phone( userProfile.getPhone() );
        userProfileResponseDto.photo( userProfile.getPhoto() );
        userProfileResponseDto.address( userProfile.getAddress() );
        userProfileResponseDto.about( userProfile.getAbout() );
        userProfileResponseDto.created( userProfile.getCreated() );
        userProfileResponseDto.updated( userProfile.getUpdated() );

        return userProfileResponseDto.build();
    }
}
