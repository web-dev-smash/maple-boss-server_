package com.maple.common.user.domain;

@FunctionalInterface
public interface CertCodeGenerator {
    String generate();
}
