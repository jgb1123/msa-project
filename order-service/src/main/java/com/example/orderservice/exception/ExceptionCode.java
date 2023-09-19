package com.example.orderservice.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    ORDER_NOT_FOUND(404, "Order not found"),
    ORDER_CANNOT_CREATE(409, "Order cannot create");

    private final int status;

    private final String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}
