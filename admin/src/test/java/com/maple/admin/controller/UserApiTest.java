package com.maple.admin.controller;

import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.service.UserService;
import com.maple.core.support.BaseApiTest;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserApiTest extends BaseApiTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CertCodeGenerator mockCertCodeGenerator;

    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private User user6;

    @BeforeEach
    void setUp() {
        user1 = userService.create(new User("id1", "password1", "nick1", "email1"), mockCertCodeGenerator);
        user2 = userService.create(new User("id2", "password2", "nick2", "email2"), mockCertCodeGenerator);
        user3 = userService.create(new User("id3", "password3", "nick3", "email3"), mockCertCodeGenerator);
        user4 = userService.create(new User("id4", "password4", "nick4", "email4"), mockCertCodeGenerator);
        user5 = userService.create(new User("id5", "password5", "nick5", "email5"), mockCertCodeGenerator);
        user6 = userService.create(new User("id6", "password6", "nick6", "email6"), mockCertCodeGenerator);
    }

    @Test
    void 전체_유저_목록_조회() throws Exception {
        mockMvc.perform(get("/user")
            .param("page", "0")
            .param("size", "4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(유저_목록_조회_검증(0, user1))
                .andExpectAll(유저_목록_조회_검증(1, user2))
                .andExpectAll(유저_목록_조회_검증(2, user3))
                .andExpectAll(유저_목록_조회_검증(3, user4));
    }

    private ResultMatcher[] 유저_목록_조회_검증(int index, User user) {
        val indexString = String.valueOf(index);

        return List.of(
                jsonPath("$.users[{index}].id".replace("{index}", indexString)).value(user.getId()),
                jsonPath("$.users[{index}].loginId".replace("{index}", indexString)).value(user.getLoginId()),
                jsonPath("$.users[{index}].nickname".replace("{index}", indexString)).value(user.getNickname()),
                jsonPath("$.users[{index}].email".replace("{index}", indexString)).value(user.getEmail()),
                jsonPath("$.users[{index}].status".replace("{index}", indexString)).value(user.getStatus().name()),
                jsonPath("$.users[{index}].createAt".replace("{index}", indexString)).isNotEmpty()
        ).toArray(ResultMatcher[]::new);
    }
}