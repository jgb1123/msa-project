package com.example.orderservice.exception;

import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException{

    private final ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode){
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
