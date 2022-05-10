package com.maple.common.user.domain;

import com.maple.common.support.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.val;
import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private CertCodeGenerator generator;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() {
        generator = new MockCertCodeGenerator();

        user1 = userRepository.save(new User("goyounha11", "1", "쩌로", "goyounha11@naver.com", generator));
        user2 = userRepository.save(new User("wook11", "1", "욱", "wook11@naver.com", generator));
        user3 = userRepository.save(new User("pabeul11", "1",  "아아", "joon@naver.com", generator));
    }

    @Test
    void 유저_등록_성공() {
        var user = new User("goyounha13", "1", "쩌로1", "goyounha13@naver.com", generator);

        user = userRepository.save(user);

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getId()).isNotNull();
        assertThat(foundUser.getLoginId()).isEqualTo(user.getLoginId());
        assertThat(foundUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(foundUser.getNickname()).isEqualTo(user.getNickname());
        assertThat(foundUser.getStatus()).isEqualTo(user.getStatus());
        assertThat(foundUser.getCreateAt()).isNotNull();
    }
}
