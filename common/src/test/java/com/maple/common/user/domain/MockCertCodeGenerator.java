package com.maple.common.user.domain;

import org.springframework.stereotype.Component;

@Component
public class MockCertCodeGenerator implements CertCodeGenerator {

    @Override
    public String generate() {
        return "code";
    }
}
