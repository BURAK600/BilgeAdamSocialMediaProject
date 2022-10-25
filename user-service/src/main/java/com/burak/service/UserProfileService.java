package com.burak.service;

import com.burak.dto.request.ActivatedRequestDto;
import com.burak.dto.request.UserCreateRequestDto;
import com.burak.dto.request.UserUpdateRequestDto;
import com.burak.dto.response.UserProfileRedisResponseDto;
import com.burak.exception.ErrorType;
import com.burak.exception.UserProfileServiceException;
import com.burak.mapper.IUserProfileMapper;
import com.burak.repository.IUserProfileRepository;
import com.burak.repository.entity.UserProfile;
import com.burak.repository.enums.Status;
import com.burak.utility.JwtTokenManager;
import com.burak.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile, Long> {

    private final IUserProfileRepository iUserProfileRepository;
    private final JwtTokenManager jwtTokenManager;

    private final CacheManager cacheManager;

    public UserProfileService(IUserProfileRepository iUserProfileRepository, JwtTokenManager jwtTokenManager, CacheManager cacheManager) {
        super(iUserProfileRepository);
        this.iUserProfileRepository = iUserProfileRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.cacheManager = cacheManager;
    }

    public UserProfile save(UserCreateRequestDto userCreateRequestDto) {
        UserProfile userProfile = IUserProfileMapper.INSTANSE.toUserProfile(userCreateRequestDto);

        return iUserProfileRepository.save(userProfile);


    }

    public Boolean activateStatus(ActivatedRequestDto activatedRequestDto) {

        Optional<UserProfile> userProfile = iUserProfileRepository.findOptinalByAuthId(activatedRequestDto.getId());

        if (userProfile.isEmpty()) {
            throw new UserProfileServiceException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(Status.ACTIVE);
        save(userProfile.get());
        return true;

    }
@Cacheable(value = "update")
    public Boolean updateUserForRedis(UserUpdateRequestDto userUpdateRequestDto) {

        Optional<Long> autId = jwtTokenManager.getByIdFromToken(userUpdateRequestDto.getToken());

//        UserProfile userProfile  = IUserProfileMapper.INSTANSE.toUserProfile(userUpdateRequestDto);

        if (autId.isPresent()) {
            Optional<UserProfile> userProfileDb = iUserProfileRepository.findOptinalByAuthId(autId.get());
            if (userProfileDb.isPresent()) {
                cacheManager.getCache("update").evict(userProfileDb.get().getUserName());

                userProfileDb.get().setEmail(userUpdateRequestDto.getEmail());
                userProfileDb.get().setPhone(userUpdateRequestDto.getPhone());
                userProfileDb.get().setAddress(userUpdateRequestDto.getAddress());
                userProfileDb.get().setAbout(userUpdateRequestDto.getAbout());
                userProfileDb.get().setPhoto(userUpdateRequestDto.getPhoto());
                userProfileDb.get().setName(userUpdateRequestDto.getName());
                userProfileDb.get().setUserName(userUpdateRequestDto.getUserName());
                save(userProfileDb.get());


                return true;

            }else{
                throw new UserProfileServiceException(ErrorType.USER_NOT_FOUND);
            }
        } else {

            throw new UserProfileServiceException(ErrorType.GECERSIZ_TOKEN);

        }





    }

    public UserProfileRedisResponseDto get() {
        return null;


    }
@Cacheable(value = "findbyusername")
    public UserProfileRedisResponseDto  findByUserName(String userName) {

        Optional<UserProfile> userProfile = iUserProfileRepository.findOptinalByUserNameEqualsIgnoreCase(userName);
        if (userProfile.isPresent()){
            return IUserProfileMapper.INSTANSE.toUserProfileRedisResponseDto(userProfile.get());
        }else {
            throw new UserProfileServiceException(ErrorType.USER_NOT_FOUND);
        }



    }

    @Cacheable(value = "findallactiveprofile")
    public List<UserProfile> findAllActiveProfile() {
        return iUserProfileRepository.getActiveProfile();
    }
}
