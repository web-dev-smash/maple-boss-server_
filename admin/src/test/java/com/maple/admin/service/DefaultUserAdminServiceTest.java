package com.maple.admin.service;

import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.service.UserService;
import com.maple.core.support.BaseServiceTest;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultUserAdminServiceTest extends BaseServiceTest {

    @Autowired
    private UserAdminService userAdminService;

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
    void 전체_유저_조회_성공() {
        val page1Users = userAdminService.getAllUsers(PageRequest.of(0, 4));
        val page2Users = userAdminService.getAllUsers(PageRequest.of(1, 4));

        assertThat(page1Users).containsExactly(user1, user2, user3, user4);
        assertThat(page2Users).containsExactly(user5, user6);
    }
}