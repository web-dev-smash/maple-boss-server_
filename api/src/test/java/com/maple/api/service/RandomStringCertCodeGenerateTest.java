package com.maple.api.service;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.maple.common.user.domain.CertCodeGenerator.LENGTH;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import(RandomStringCertCodeGenerate.class)
public class RandomStringCertCodeGenerateTest {

    @Autowired
    private RandomStringCertCodeGenerate randomStringCertCodeGenerate;

    @Test
    void CertCode_길이_검증() {
        val certCode = randomStringCertCodeGenerate.generate();

        assertThat(certCode.length()).isEqualTo(LENGTH);
    }
}