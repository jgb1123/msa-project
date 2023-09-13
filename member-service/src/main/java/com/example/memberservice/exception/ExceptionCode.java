package com.example.memberservice.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found");

    private final int status;

    private final String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}
