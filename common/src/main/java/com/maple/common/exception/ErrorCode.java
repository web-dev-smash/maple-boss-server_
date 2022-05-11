package com.maple.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    ALREADY_USED_PASSWORD("error.already.used.password"),
    INVALID_CERT_CODE("error.invalid.cert.code"),
    ALREADY_EXISTS_PARTY_MEMBER("error.already.exists.party.member"),
    ALREADY_MAXIMUM_PARTY_MEMBER("error.already.maximum.party.member"),
    NOT_EXISTS_PARTY_MEMBER("error.not.exists.party.member");

    private final String message;
}
