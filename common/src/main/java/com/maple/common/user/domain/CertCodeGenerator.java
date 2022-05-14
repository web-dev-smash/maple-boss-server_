package com.maple.common.user.domain;

@FunctionalInterface
public interface CertCodeGenerator {

    int LENGTH = 5;

    String generate();
}
