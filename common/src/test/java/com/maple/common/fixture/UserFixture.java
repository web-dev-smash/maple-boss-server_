package com.maple.common.fixture;

import com.maple.common.user.domain.MockCertCodeGenerator;
import com.maple.common.user.domain.User;

public class UserFixture {

    public static User createUser() {
        return new User("goyounha11", "1", "쩌로", "goyounha11@naver.com", new MockCertCodeGenerator());
    }
}
