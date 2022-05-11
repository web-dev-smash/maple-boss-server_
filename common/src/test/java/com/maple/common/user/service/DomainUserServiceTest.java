package com.maple.common.user.service;

import com.maple.common.support.BaseServiceTest;
import com.maple.common.user.domain.MockCertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class DomainUserServiceTest extends BaseServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void 유저_생성_성공() {
        var user = new User("goyounha11", "1", "쩌로", "goyounha11@naver.com", new MockCertCodeGenerator());

        user = userRepository.save(user);

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

       assertThat(foundUser).isEqualTo(user);
    }
}
