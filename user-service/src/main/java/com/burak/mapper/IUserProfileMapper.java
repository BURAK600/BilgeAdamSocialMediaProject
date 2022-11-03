package com.burak.mapper;

import com.burak.dto.request.UserCreateRequestDto;
import com.burak.dto.request.UserUpdateRequestDto;
import com.burak.dto.response.UserProfileRedisResponseDto;
import com.burak.dto.response.UserProfileResponseDto;
import com.burak.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {

    IUserProfileMapper INSTANSE = Mappers.getMapper(IUserProfileMapper.class);


    UserProfile toUserProfile(final UserCreateRequestDto userCreateRequestDto);

    UserCreateRequestDto toUserCreateRequestDto(final UserProfile userProfile);

    UserProfile toUserProfile(final UserUpdateRequestDto userUpdateRequestDto);

    UserProfileRedisResponseDto toUserProfileRedisResponseDto(final UserProfile userProfile);

    List<UserProfileResponseDto>  toUserProfileResponseDto(final List<UserProfile> userProfile);



}
