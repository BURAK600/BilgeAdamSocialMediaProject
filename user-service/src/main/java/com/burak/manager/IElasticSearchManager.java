package com.burak.manager;


import com.burak.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.burak.constants.ApiUrls.SAVE;
import static com.burak.constants.ApiUrls.UPDATE;

@FeignClient(name = "elastic-service", url = "${myapplication.elastic-service.feign-elastic}/elastic", decode404 = true)
public interface IElasticSearchManager {

    @PostMapping(SAVE)
    ResponseEntity<UserProfile> createUser(@RequestBody UserProfile userProfile);

    @PostMapping(UPDATE)
    ResponseEntity<UserProfile> updateUser(@RequestBody UserProfile userProfile);
}
