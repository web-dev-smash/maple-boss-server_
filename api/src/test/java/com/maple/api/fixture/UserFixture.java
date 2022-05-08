package com.maple.api.fixture;

import com.maple.common.user.domain.User;

public class UserFixture {

    public static User createUser() {
        return new User("goyounha11", "1", "신철호", "쩌로", "goyounha11@naver.com");
    }
}
