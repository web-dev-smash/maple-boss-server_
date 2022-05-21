package com.maple.api.service;

import com.maple.api.service.dto.UserCreateDto;
import com.maple.api.service.dto.UserPrepareWithdrawalDto;
import com.maple.common.user.domain.UserRepository;
import com.maple.core.support.BaseServiceTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;

import static com.maple.api.fixture.UserFixture.createUser;
import static com.maple.common.user.domain.User.CERTIFICATE_MINUTES;
import static com.maple.common.user.domain.UserStatus.ACTIVATED;
import static com.maple.common.user.domain.UserStatus.INACTIVATING;
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

    @Test
    void 인증코드_요청() {
        var user = createUser();

        user.status = ACTIVATED;
        user.certCode = "code";

        user = userRepository.save(user);

        val beforeCertCode = user.getCertCode();

        userAppService.requestCertCode(user.getId());

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getCertCode()).isNotEqualTo(beforeCertCode);
    }

    @Test
    void 탈퇴_준비() {
        var user = createUser();

        user.status = ACTIVATED;
        user.certCode = "INACTIVATING_CODE";
        user.certCodeSentAt = OffsetDateTime.now().minusMinutes(CERTIFICATE_MINUTES).minusSeconds(10);

        user = userRepository.save(user);

        val dto = new UserPrepareWithdrawalDto(user.getId(), "INACTIVATING_CODE");

        userAppService.prepareWithdrawal(dto);

        val foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getStatus()).isEqualTo(INACTIVATING);
    }
}