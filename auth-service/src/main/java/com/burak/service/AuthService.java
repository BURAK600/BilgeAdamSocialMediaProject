package com.burak.service;

import com.burak.dto.request.ActivatedRequestDto;
import com.burak.dto.request.AuthLoginRequestDto;
import com.burak.dto.request.AuthRegisterRequestDto;
import com.burak.dto.request.UserCreateRequestDto;
import com.burak.dto.response.AuthLoginResponseDto;
import com.burak.dto.response.AuthRegisterResponseDto;
import com.burak.exception.AuthServiceException;
import com.burak.exception.ErrorType;
import com.burak.manager.IUserProfileManager;
import com.burak.mapper.IAuthMapper;
import com.burak.repository.IAuthRepository;
import com.burak.repository.entity.Auth;
import com.burak.repository.enums.Roles;
import com.burak.repository.enums.Status;
import com.burak.utility.CodeGenerator;
import com.burak.utility.JwtTokenManager;
import com.burak.utility.ServiceManager;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class AuthService extends ServiceManager<Auth, Long> {


    private final IUserProfileManager iUserProfileManager;
    private final IAuthRepository iAuthRepository;

    private final JwtTokenManager jwtTokenManager;



    public AuthService(IUserProfileManager iUserProfileManager, IAuthRepository iAuthRepository, JwtTokenManager jwtTokenManager) {
        super(iAuthRepository);

        this.iUserProfileManager = iUserProfileManager;
        this.iAuthRepository = iAuthRepository;

        this.jwtTokenManager = jwtTokenManager;
    }

    public Boolean save(AuthRegisterRequestDto authRegisterRequestDto){
        Auth auth = Auth.builder().userName(authRegisterRequestDto.getUserName())
                .password(authRegisterRequestDto.getPassword())
        .email(authRegisterRequestDto.getEmail()).adminCode(authRegisterRequestDto.getAdminCode())
                .build();
        save(auth);
        return true;
    }



    public AuthRegisterResponseDto register(AuthRegisterRequestDto authRegisterRequestDto) {
        Auth auth = IAuthMapper.INSTANSE.toAuth(authRegisterRequestDto);

//        if(userIsExist(authRegisterRequestDto.getUserName())){
//            throw new AuthServiceException(ErrorType.LOGIN_ERROR_USERNAME_DUPLICATE);
//        }else{
//            if(authRegisterRequestDto.getAdminCode()!=null&& authRegisterRequestDto.getAdminCode().equals("admin")){
//                auth.setRole(Roles.ADMIN);
//            }
//            try {
//                return save(auth);
//            }catch (Exception e){
//                throw new AuthServiceException(ErrorType.USER_NOT_CREATED);
//            }
        if(authRegisterRequestDto.getAdminCode()!=null && authRegisterRequestDto.getAdminCode().equals("admin")){
            auth.setRole((Roles.ADMIN));
        }
        try {
            auth.setActivatedCode(CodeGenerator.generatedCode(UUID.randomUUID().toString()));
            save(auth);
            iUserProfileManager.save(UserCreateRequestDto.builder()
                            .authId(auth.getId())
                            .email(auth.getEmail())
                            .userName(auth.getUserName())
                    .build());
            return IAuthMapper.INSTANSE.toAuthRegisterResponseDto(auth);
        }catch (AuthServiceException a){
            throw new AuthServiceException(ErrorType.USER_NOT_CREATED);
        }

    }

    public Boolean userIsExist(String userName){
        return iAuthRepository.existUserName(userName);

    }

    public Optional<AuthLoginResponseDto> dologin(AuthLoginRequestDto authLoginRequestDto) {
        Optional<Auth> auth = iAuthRepository.findOptionalByUserNameAndPassword(authLoginRequestDto.getUserName(),
                authLoginRequestDto.getPassword());


        if(auth.isPresent()){
            AuthLoginResponseDto authLoginResponseDto = IAuthMapper.INSTANSE.toAuthLoginResponse(auth.get());
            String token = jwtTokenManager.createToken(authLoginResponseDto.getId());
            authLoginResponseDto.setToken(token);
            return Optional.of(authLoginResponseDto);
        }else{
            throw new AuthServiceException(ErrorType.LOGIN_ERROR_WRONG);
        }
    }

    public  Boolean activatedStatus(ActivatedRequestDto activatedRequestDto) {
        Optional<Auth> auth = iAuthRepository.findById(activatedRequestDto.getId());

        if(auth.isEmpty()){
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        }
        if(auth.get().getActivatedCode().equals(activatedRequestDto.getActivatedCode())){
            auth.get().setStatus(Status.ACTIVE);
            iUserProfileManager.activateStatus(activatedRequestDto);
            save(auth.get());

            return true;
        }
        throw new AuthServiceException(ErrorType.INVALID_ACTIVATED_CODE);

    }



    @Cacheable(value = "reddisgetupper")
    public String getUpperCase(String name) {
        try{
            Thread.sleep(3000);

        }catch (Exception e){

        }
        return name.toUpperCase();
    }
}
