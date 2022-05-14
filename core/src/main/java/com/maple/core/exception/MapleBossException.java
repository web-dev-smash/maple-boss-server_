package com.maple.core.exception;

public class MapleBossException extends RuntimeException {

    public MapleBossException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }

    public MapleBossException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
    }

    public static void validate(boolean expression, ErrorCode errorCode) {
        if (!expression) {
            throw new MapleBossException(errorCode);
        }
    }
}
