package com.maple.common.user.domain;

public class MockCertCodeGenerator implements CertCodeGenerator{

    @Override
    public String generate() {
        return "code";
    }
}
