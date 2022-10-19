package com.burak.mapper;

import com.burak.dto.request.UserCreateRequestDto;
import com.burak.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {

    IUserProfileMapper INSTANSE = Mappers.getMapper(IUserProfileMapper.class);


    UserProfile toUserProfile(final UserCreateRequestDto userCreateRequestDto);

    UserCreateRequestDto toUserCreateRequestDto(final UserProfile userProfile);

}
