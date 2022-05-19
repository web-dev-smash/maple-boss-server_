package com.maple.api.controller;

import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import com.maple.common.user.service.UserService;
import com.maple.core.support.BaseApiTest;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.maple.api.controller.dto.UserCreateDto.UserCreateRequest;
import static com.maple.api.fixture.UserFixture.createUser;
import static com.maple.common.user.domain.UserStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserApiTest extends BaseApiTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CertCodeGenerator certCodeGenerator;

    private User user;

    @BeforeEach
    void setUp() {
        user = userService.create(createUser(), certCodeGenerator);
    }

    @Test
    void 유저_가입() throws Exception {
        val req = new UserCreateRequest("woogie", "1234", "우기", "woogie@gmail.com");

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(req)))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.user.id").isNotEmpty(),
                        jsonPath("$.user.loginId").value(req.getLoginId()),
                        jsonPath("$.user.nickname").value(req.getNickname()),
                        jsonPath("$.user.email").value(req.getEmail()),
                        jsonPath("$.user.status").value(CREATED.name()),
                        jsonPath("$.user.createAt").isNotEmpty()
                );
    }

    @Test
    void 유저_탈퇴_준비() throws Exception {
        user.status = ACTIVATED;
        user.certCode = "INACTIVATING_CODE";

        mockMvc.perform(post("/user/{id}/{certCode}/prepare-withdrawal", user.getId(), "INACTIVATING_CODE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getStatus()).isEqualTo(INACTIVATING);
    }
}