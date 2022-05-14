package com.maple.common.user.service;

import com.maple.common.support.BaseServiceTest;
import com.maple.common.user.domain.MockCertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserRepository;
import com.maple.common.user.domain.UserStatus;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;

import static com.maple.common.exception.ErrorCode.INVALID_CERT_CODE;
import static com.maple.common.fixture.UserFixture.createUser;
import static com.maple.common.support.MapleBossExceptionTest.assertThatMapleBossException;
import static com.maple.common.user.domain.User.CERTIFICATE_MINUTES;
import static com.maple.common.user.domain.UserStatus.INACTIVATING;
import static org.assertj.core.api.Assertions.assertThat;

class DomainUserServiceTest extends BaseServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = userService.create(createUser());
    }

    @Test
    void 유저_생성_성공() {
        var user = new User("peubel", "1", "아아", "peubel@naver.com", new MockCertCodeGenerator());

        user = userService.create(user);

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser).isEqualTo(user);
    }

    @Test
    void 활성화_처리() {
        val currentTime = OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES + 1);

        userService.activate(user.getId(), currentTime, new MockCertCodeGenerator());

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getStatus()).isEqualTo(UserStatus.ACTIVATED);
    }

    @Test
    void 인증코드가_다르면_활성화_실패() {
        val currentTime = OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES + 1);

        assertThatMapleBossException(INVALID_CERT_CODE)
                .isThrownBy(() -> userService.activate(user.getId(), currentTime, () -> "FAKE_CODE"));
    }

    @Test
    void 탈퇴_준비() {
        user.activate(OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES + 1));

        userService.prepareWithdrawal(user.getId());

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getStatus()).isEqualTo(INACTIVATING);
    }
}
