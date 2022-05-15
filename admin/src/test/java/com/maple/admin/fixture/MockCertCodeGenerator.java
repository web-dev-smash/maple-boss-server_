package com.maple.admin.fixture;

import com.maple.common.user.domain.CertCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class MockCertCodeGenerator implements CertCodeGenerator {

    @Override
    public String generate() {
        return "code";
    }
}
