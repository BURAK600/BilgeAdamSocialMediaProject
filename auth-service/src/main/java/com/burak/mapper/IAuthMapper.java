package com.burak.mapper;


import com.burak.dto.request.AuthLoginRequestDto;
import com.burak.dto.request.AuthRegisterRequestDto;
import com.burak.dto.response.ActivePendingUserResponseDto;
import com.burak.dto.response.AuthLoginResponseDto;
import com.burak.dto.response.AuthRegisterResponseDto;
import com.burak.dto.response.RoleResponseDto;
import com.burak.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANSE = Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(final AuthRegisterRequestDto authRegisterRequestDto);
    AuthRegisterRequestDto toAuthRegisterRequestDto(final Auth auth);


    AuthLoginResponseDto toAuthLoginResponse(final Auth auth);


    AuthLoginRequestDto toLoginRequestDto(final Auth auth);
    Auth toAuth(final AuthLoginRequestDto authLoginRequestDto);

    AuthLoginResponseDto toAuthLoginResponseDto(final Auth auth);
    AuthRegisterResponseDto toAuthRegisterResponseDto(final Auth auth);

    List<ActivePendingUserResponseDto> toActivePendingUserResponseDto(final List<Auth> auth);

    RoleResponseDto toRoleResponseDto(final Auth auth);




}
