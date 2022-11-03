package com.burak.manager;


import com.burak.dto.response.RoleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "auth-service", decode404 = true, url = "${myapplication.auth.feign-auth}/auth")
public interface IAuthManager {

    @GetMapping("/findbyrole")
    ResponseEntity<List<RoleResponseDto>> findRoles(String roles);


}
