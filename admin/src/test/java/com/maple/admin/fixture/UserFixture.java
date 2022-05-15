package com.maple.admin.fixture;

import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;

public class UserFixture {

    public static User createUser() {
        return new User("goyounha11", "1", "쩌로", "goyounha11@naver.com");
    }

    public static class MockCertCodeGenerator implements CertCodeGenerator {

        @Override
        public String generate() {
            return "code";
        }
    }
}
