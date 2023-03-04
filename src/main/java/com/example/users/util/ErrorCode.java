package com.example.users.util;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATE_ID(1000),
    USER_NOT_FOUND(1100);

    private final int errorCode;

    ErrorCode(int errorCode1) {
        this.errorCode = errorCode1;
    }


}
