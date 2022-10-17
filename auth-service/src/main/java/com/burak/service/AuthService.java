package com.burak.service;

import com.burak.repository.IAuthRepository;
import com.burak.repository.entity.Auth;
import com.burak.utility.ServiceManager;

import org.springframework.stereotype.Service;


@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository iAuthRepository;

    public AuthService(IAuthRepository iAuthRepository) {
        super(iAuthRepository);
        this.iAuthRepository = iAuthRepository;
    }
}
