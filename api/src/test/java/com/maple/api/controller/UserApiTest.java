package com.maple.api.controller;

import com.maple.api.fixture.UserFixture;
import com.maple.api.support.BaseApiTest;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import com.maple.common.user.domain.UserStatus;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;

import static com.maple.common.user.domain.User.CERTIFICATE_MINUTES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserApiTest extends BaseApiTest {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {
        user = userRepository.save(UserFixture.createUser());
    }

    @Test
    void 회원_탈퇴_준비() throws Exception {
        user.activate(OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES + 1));

        mockMvc.perform(post("/user/{id}/prepare-withdrawal", user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getStatus()).isEqualTo(UserStatus.INACTIVATING);
    }
}