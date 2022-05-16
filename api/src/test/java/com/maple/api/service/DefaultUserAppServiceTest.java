package com.maple.api.service;

import com.maple.api.service.dto.UserCreateDto;
import com.maple.common.user.domain.UserRepository;
import com.maple.core.support.BaseServiceTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultUserAppServiceTest extends BaseServiceTest {

    @Autowired
    private UserAppService userAppService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 유저_생성_성공() {
        val userCreateDto = new UserCreateDto("woogie", "1234", "우기", "woogie@gmail.com");

        val user = userAppService.create(userCreateDto);

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getLoginId()).isEqualTo("woogie");
        assertThat(foundUser.getPassword()).isEqualTo("1234");
        assertThat(foundUser.getNickname()).isEqualTo("우기");
        assertThat(foundUser.getEmail()).isEqualTo("woogie@gmail.com");
    }
}