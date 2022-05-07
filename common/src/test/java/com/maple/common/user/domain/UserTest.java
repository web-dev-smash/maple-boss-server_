package com.maple.common.user.domain;

import lombok.val;
import org.junit.jupiter.api.Test;

import static com.maple.common.user.domain.UserStatus.CREATED;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    
    @Test
    void User_생성_성공() {
        val user = new User("goyounha11", "1", "신철호", "쩌로", "goyounha11@naver.com");

        assertThat(user.getLoginId()).isEqualTo("goyounha11");
        assertThat(user.getPassword()).isEqualTo("1");
        assertThat(user.getName()).isEqualTo("신철호");
        assertThat(user.getNickname()).isEqualTo("쩌로");
        assertThat(user.getEmail()).isEqualTo("goyounha11@naver.com");
        assertThat(user.getStatus()).isEqualTo(CREATED);
        assertThat(user.getCreateAt()).isNotNull();
    }
}
