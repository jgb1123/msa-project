package com.example.storeservice.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    STORE_NOT_FOUND(404, "Store not found"),
    STORE_CATEGORY_NOT_FOUND(404, "StoreCategory not found");

    private final int status;

    private final String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}
