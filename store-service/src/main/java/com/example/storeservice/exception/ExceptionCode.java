package com.example.storeservice.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    STORE_NOT_FOUND(404, "Store not found"),
    STORE_CATEGORY_NOT_FOUND(404, "StoreCategory not found"),
    ITEM_NOT_FOUND(404, "Item not found"),
    ITEM_CANNOT_CREATE(404, "Item cannot create");

    private final int status;

    private final String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}
