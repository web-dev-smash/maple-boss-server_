package com.maple.common.user.service;

import com.maple.common.support.BaseServiceTest;
import com.maple.common.user.domain.MockCertCodeGenerator;
import lombok.val;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DomainUserServiceTest extends BaseServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DomainUserService domainUserService;

    @Test
    void 유저_생성_성공() {
        var user = new User("goyounha11", "1", "쩌로", "goyounha11@naver.com", new MockCertCodeGenerator());

        user = userRepository.save(user);

        assertThat(user.getId()).isNotNull();

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getId()).isEqualTo(user.getId());
        assertThat(foundUser.getLoginId()).isEqualTo(user.getLoginId());
        assertThat(foundUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(foundUser.getNickname()).isEqualTo(user.getNickname());
        assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(foundUser.getStatus()).isEqualTo(user.getStatus());
        assertThat(foundUser.getCreateAt()).isNotNull();
    }
}
