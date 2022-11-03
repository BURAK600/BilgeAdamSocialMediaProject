package com.burak.mapper;


import com.burak.dto.response.UserProfileResponseDto;
import com.burak.grapql.model.UserProfileInput;
import com.burak.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {


    IUserProfileMapper INSTANSE = Mappers.getMapper(IUserProfileMapper.class);

//    @Mapping(source = "id", target = "userId")
   UserProfile toUserProfile(final UserProfileResponseDto userProfileResponseDto);


    List<UserProfileResponseDto>  toUserProfileResponseDto(final List<UserProfile> userProfile);

    UserProfile toUserProfile(final UserProfileInput userProfileInput);



}
