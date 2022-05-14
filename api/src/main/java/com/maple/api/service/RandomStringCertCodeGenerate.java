package com.maple.api.service;

import com.maple.common.user.domain.CertCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class RandomStringCertCodeGenerate implements CertCodeGenerator {

    @Override
    public String generate() {
        return RandomStringUtils.randomAlphanumeric(LENGTH);
    }
}
