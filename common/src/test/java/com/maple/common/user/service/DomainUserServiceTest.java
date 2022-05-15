package com.maple.common.user.service;

import com.maple.common.support.BaseServiceTest;
import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import com.maple.common.user.domain.UserStatus;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;

import static com.maple.common.fixture.UserFixture.createUser;
import static com.maple.common.user.domain.User.CERTIFICATE_MINUTES;
import static com.maple.common.user.domain.UserStatus.INACTIVATING;
import static com.maple.core.exception.ErrorCode.*;
import static com.maple.core.support.MapleBossExceptionTest.assertThatMapleBossException;
import static org.assertj.core.api.Assertions.assertThat;

class DomainUserServiceTest extends BaseServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CertCodeGenerator mockCertCodeGenerator;

    private User user;

    @BeforeEach
    void setUp() {
        user = userService.create(createUser(), mockCertCodeGenerator);
    }

    @Test
    void 유저_생성_성공() {
        var user = new User("peubel", "1", "아아", "peubel@naver.com");

        user = userService.create(user, mockCertCodeGenerator);

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    void 활성화_처리() {
        val currentTime = OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES + 1);

        userService.activate(user.getId(), "code", currentTime);

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getStatus()).isEqualTo(UserStatus.ACTIVATED);
    }

    @Test
    void 인증코드가_다르면_활성화_실패() {
        val currentTime = OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES + 1);

        assertThatMapleBossException(INVALID_CERT_CODE)
                .isThrownBy(() -> userService.activate(user.getId(), "FAKE_CODE", currentTime));
    }

    @Test
    void 탈퇴_준비() {
        user.activate(user.getCertCode(), OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES + 1));

        userService.prepareWithdrawal(user.getId());

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getStatus()).isEqualTo(INACTIVATING);
    }

    @Test
    void 이미_로그인아이디가_존재하면_실패() {
        val fakeUser = new User(user.getLoginId(), "1", "FAKE_USER", "FAKE_USER@naver.com");

        assertThatMapleBossException(ALREADY_EXISTS_LOGIN_ID).isThrownBy(() -> userService.create(fakeUser, mockCertCodeGenerator));
    }

    @Test
    void 이미_이메일이_존재하면_실패() {
        val fakeUser = new User("FAKE_USER", "1", "FAKE_USER", user.getEmail());

        assertThatMapleBossException(ALREADY_EXISTS_EMAIL).isThrownBy(() -> userService.create(fakeUser, mockCertCodeGenerator));
    }
}
