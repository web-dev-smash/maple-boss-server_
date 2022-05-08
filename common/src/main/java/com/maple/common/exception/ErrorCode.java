package com.maple.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    TEST_HELLO_WORLD("test.hello.world"),

    ALREADY_EXISTS_PARTY_MEMBER("error.already.exists.party.member"),
    ALREADY_MAXIMUM_PARTY_MEMBER("error.already.maximum.party.member"),
    NOT_EXISTS_PARTY_MEMBER("error.not.exists.party.member");

    private final String message;
}
