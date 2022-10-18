package com.burak.service;

import com.burak.dto.request.AuthLoginRequestDto;
import com.burak.dto.request.AuthRegisterRequestDto;
import com.burak.dto.response.AuthLoginResponseDto;
import com.burak.exception.AuthServiceException;
import com.burak.exception.ErrorType;
import com.burak.mapper.IAuthMapper;
import com.burak.repository.IAuthRepository;
import com.burak.repository.entity.Auth;
import com.burak.repository.enums.Roles;
import com.burak.utility.ServiceManager;

import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;


@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository iAuthRepository;

    public AuthService(IAuthRepository iAuthRepository) {
        super(iAuthRepository);
        this.iAuthRepository = iAuthRepository;
    }

    public Boolean save(AuthRegisterRequestDto authRegisterRequestDto){
        Auth auth = Auth.builder().userName(authRegisterRequestDto.getUserName())
                .password(authRegisterRequestDto.getPassword())
        .email(authRegisterRequestDto.getEmail()).adminCode(authRegisterRequestDto.getAdminCode())
                .build();
        save(auth);
        return true;
    }



    public Auth register(AuthRegisterRequestDto authRegisterRequestDto) {
        Auth auth = IAuthMapper.INSTANSE.toAuth(authRegisterRequestDto);

        if(userIsExist(authRegisterRequestDto.getUserName())){
            throw new AuthServiceException(ErrorType.LOGIN_ERROR_USERNAME_DUPLICATE);
        }else{
            if(authRegisterRequestDto.getAdminCode()!=null&& authRegisterRequestDto.getAdminCode().equals("admin")){
                auth.setRole(Roles.ADMIN);
            }
            try {
                return save(auth);
            }catch (Exception e){
                throw new AuthServiceException(ErrorType.USER_NOT_CREATED);
            }

        }


    }

    public Boolean userIsExist(String userName){
        return iAuthRepository.existUserName(userName);

    }

    public Optional<AuthLoginResponseDto> dologin(AuthLoginRequestDto authLoginRequestDto) {
        Optional<Auth> auth = iAuthRepository.findOptionalByUserNameAndPassword(authLoginRequestDto.getUserName(),
                authLoginRequestDto.getPassword());

        if(auth.isPresent()){
            return Optional.of(IAuthMapper.INSTANSE.toAuthLoginResponse(auth.get()));

        }else{
            throw new AuthServiceException(ErrorType.LOGIN_ERROR_WRONG);
        }

    }
}
