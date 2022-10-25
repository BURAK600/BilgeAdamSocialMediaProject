package com.burak.exception;

import lombok.Getter;

@Getter
public class UserProfileServiceException extends RuntimeException {

    private final ErrorType errorType;

    public UserProfileServiceException(ErrorType errorType){
        super(errorType.getMessage());

        this.errorType = errorType;

    }

    public UserProfileServiceException(ErrorType errorType, String message){

        super(message);
        this.errorType = errorType;

    }
}
