package com.burak.service;

import com.burak.dto.request.ActivatedRequestDto;
import com.burak.dto.request.UserCreateRequestDto;
import com.burak.dto.request.UserUpdateRequestDto;
import com.burak.dto.response.RoleResponseDto;
import com.burak.dto.response.UserProfileRedisResponseDto;
import com.burak.exception.ErrorType;
import com.burak.exception.UserProfileServiceException;
import com.burak.manager.IAuthManager;
import com.burak.manager.IElasticSearchManager;
import com.burak.mapper.IUserProfileMapper;
import com.burak.rabbitmq.model.UpdateUserNameEmailModel;
import com.burak.rabbitmq.producer.UpdateUserProcedure;
import com.burak.repository.IUserProfileRepository;
import com.burak.repository.entity.UserProfile;
import com.burak.repository.enums.Status;
import com.burak.utility.JwtTokenManager;
import com.burak.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile, String> {

    private final IUserProfileRepository iUserProfileRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CacheManager cacheManager;
    private final IAuthManager iAuthManager;
    private final IElasticSearchManager iElasticSearchManager;

    private final UpdateUserProcedure updateUserProcedure;
    public UserProfileService(IUserProfileRepository iUserProfileRepository, JwtTokenManager jwtTokenManager, CacheManager cacheManager, IAuthManager iAuthManager, IElasticSearchManager iElasticSearchManager, UpdateUserProcedure updateUserProcedure) {
        super(iUserProfileRepository);
        this.iUserProfileRepository = iUserProfileRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.cacheManager = cacheManager;
        this.iAuthManager = iAuthManager;
        this.iElasticSearchManager = iElasticSearchManager;
        this.updateUserProcedure = updateUserProcedure;
    }
    @Transactional
    public UserProfile save(UserCreateRequestDto userCreateRequestDto) {

        try {

        UserProfile userProfile = IUserProfileMapper.INSTANSE.toUserProfile(userCreateRequestDto);

        iUserProfileRepository.save(userProfile);

        iElasticSearchManager.createUser(userProfile);

        cacheManager.getCache("findrole").evict(userProfile.getAuthId());
        return userProfile;
        }catch (Exception e){
            throw new UserProfileServiceException(ErrorType.USER_NOT_CREATED);
        }


    }

    public Boolean deleteUser(Long id) {
        Optional<UserProfile> userProfile = iUserProfileRepository.findOptionalByAuthId(id);

        if(userProfile.isPresent()){
            userProfile.get().setStatus(Status.DELETED);
            save(userProfile.get());
            return true;
        }else{
            throw new UserProfileServiceException(ErrorType.USER_NOT_FOUND);
        }
    }


    public Boolean activateStatus(ActivatedRequestDto activatedRequestDto) {

        Optional<UserProfile> userProfile = iUserProfileRepository.findOptinalByAuthId(activatedRequestDto.getId());

        if (userProfile.isEmpty()) {
            throw new UserProfileServiceException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(Status.ACTIVE);
        cacheManager.getCache("findbyusername").evict(userProfile.get().getStatus());
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
                cacheManager.getCache("update").evict(userProfileDb.get().getUserName().toUpperCase());

                userProfileDb.get().setEmail(userUpdateRequestDto.getEmail());
                userProfileDb.get().setPhone(userUpdateRequestDto.getPhone());
                userProfileDb.get().setAddress(userUpdateRequestDto.getAddress());
                userProfileDb.get().setAbout(userUpdateRequestDto.getAbout());
                userProfileDb.get().setPhoto(userUpdateRequestDto.getPhoto());
                userProfileDb.get().setName(userUpdateRequestDto.getName());
                userProfileDb.get().setUserName(userUpdateRequestDto.getUserName());
                save(userProfileDb.get());
                iElasticSearchManager.updateUser(userProfileDb.get());
                return true;
            }else{
                throw new UserProfileServiceException(ErrorType.USER_NOT_FOUND);
            }
        } else {
            throw new UserProfileServiceException(ErrorType.GECERSIZ_TOKEN);
        }
    }

    public Boolean updateUserWithRabbitMQ(UserUpdateRequestDto userUpdateRequestDto) {

        Optional<Long> autId = jwtTokenManager.getByIdFromToken(userUpdateRequestDto.getToken());
        if (autId.isPresent()) {
            Optional<UserProfile> userProfileDb = iUserProfileRepository.findOptinalByAuthId(autId.get());
            if (userProfileDb.isPresent()) {
                boolean check = checkingUserNameAndEmail(userUpdateRequestDto, userProfileDb.get());
                cacheManager.getCache("update").evict(userProfileDb.get().getUserName().toUpperCase());
                userProfileDb.get().setEmail(userUpdateRequestDto.getEmail());
                userProfileDb.get().setPhone(userUpdateRequestDto.getPhone());
                userProfileDb.get().setAddress(userUpdateRequestDto.getAddress());
                userProfileDb.get().setAbout(userUpdateRequestDto.getAbout());
                userProfileDb.get().setPhoto(userUpdateRequestDto.getPhoto());
                userProfileDb.get().setName(userUpdateRequestDto.getName());
                userProfileDb.get().setUserName(userUpdateRequestDto.getUserName());
                save(userProfileDb.get());
                if(check){
                    updateUserProcedure.sendUpdateUser(UpdateUserNameEmailModel.builder()
                            .email(userProfileDb.get().getEmail())
                            .userName(userProfileDb.get().getUserName())
                                    .id(userProfileDb.get().getAuthId())
                            .build());
                }
                iElasticSearchManager.updateUser(userProfileDb.get());
                return true;
            }else{
                throw new UserProfileServiceException(ErrorType.USER_NOT_FOUND);
            }
        } else {
            throw new UserProfileServiceException(ErrorType.GECERSIZ_TOKEN);
        }
    }

    public Boolean checkingUserNameAndEmail(UserUpdateRequestDto userUpdateRequestDto, UserProfile userProfile){
        boolean check = false;
        if(!userUpdateRequestDto.getUserName().equals(userProfile.getUserName())){
            check = true;

        }
        if(!userUpdateRequestDto.getEmail().equals(userProfile.getEmail())){
            check = true;
        }
        return false;

    }

@Cacheable(value = "findbyusername", key = "#username.toUpperCase()")
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

@Cacheable(value = "findbyrole", key = "#roles.toUpperCase()")
    public List<RoleResponseDto> findByRole(String roles) {
        return iAuthManager.findRoles(roles).getBody();

    }

    public Page<UserProfile> findAllPage(int pageSize, int pageNumber, String direction,String sortParameter) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortParameter);
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort );
        return iUserProfileRepository.findAll(pageable);

    }
}
