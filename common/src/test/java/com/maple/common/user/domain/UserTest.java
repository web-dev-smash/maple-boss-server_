package com.maple.common.user.domain;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.OffsetDateTime;

import static com.maple.common.fixture.UserFixture.createUser;
import static com.maple.common.user.domain.User.CERTIFICATE_MINUTES;
import static com.maple.common.user.domain.UserStatus.*;
import static com.maple.core.exception.ErrorCode.ALREADY_USED_PASSWORD;
import static com.maple.core.exception.ErrorCode.INVALID_CERT_CODE;
import static com.maple.core.support.MapleBossExceptionTest.assertThatMapleBossException;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

class UserTest {

    private CertCodeGenerator mockCertCodeGenerator;
    private User user;

    @BeforeEach
    void setUp() {
        mockCertCodeGenerator = new MockCertCodeGenerator();

        user = createUser();
        user.prepareCertCode(mockCertCodeGenerator);
    }

    @Test
    void 유저_생성_성공() {
        val user = new User("goyounha11", "1", "쩌로", "goyounha11@naver.com");

        assertThat(user.getLoginId()).isEqualTo("goyounha11");
        assertThat(user.getPassword()).isEqualTo("1");
        assertThat(user.getNickname()).isEqualTo("쩌로");
        assertThat(user.getEmail()).isEqualTo("goyounha11@naver.com");
        assertThat(user.getCertCode()).isNull();
        assertThat(user.getStatus()).isEqualTo(CREATED);
        assertThat(user.getCreateAt()).isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_생성_실패__로그인아이디가_null_이거나_빈값(String loginId) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User(loginId, "1", "쩌로", "goyounha11@naver.com"));
    }

    @Test
    void 유저_생성_실패__로그인아이디_공백() {
        val loginId = "     ";
        assertThatIllegalArgumentException().isThrownBy(() -> new User(loginId, "1", "쩌로", "goyounha11@naver.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_생성_실패__비밀번호가_null_이거나_빈값(String password) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", password, "쩌로", "goyounha11@naver.com"));
    }

    @Test
    void 유저_생성_실패__비밀번호가_공백() {
        val password = "     ";
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", password, "쩌로", "goyounha11@naver.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_생성_실패__닉네임이_null_이거나_빈값(String nickname) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", "1", nickname, "goyounha11@naver.com"));
    }

    @Test
    void 유저_생성_실패__유저_닉네임이_공백() {
        val nickname = "     ";
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", "1", nickname, "goyounha11@naver.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_생성_실패__이메일이_null_이거나_빈값(String email) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", "1", "쩌로", email));
    }

    @Test
    void 유저_이메일이_공백이면_실패() {
        val email = "     ";
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", "1", "쩌로", email));
    }

    @Test
    void 인증코드_준비_성공() {
        user.prepareCertCode(mockCertCodeGenerator);

        assertThat(user.getCertCode()).isEqualTo("code");
    }

    @Test
    void 활성화_성공() {
        user.activate(user.getCertCode(), OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES).plusSeconds(10));

        assertThat(user.getStatus()).isEqualTo(ACTIVATED);
    }

    @Test
    void 활성화_실패__인증코드_null() {
        assertThatNullPointerException()
                .isThrownBy(() -> user.activate(null, OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES).plusSeconds(10)));
    }

    @Test
    void 활성화_실패__인증코드_시간_만료() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> user.activate(user.getCertCode(), OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES).minusSeconds(10)));
    }

    @ParameterizedTest
    @EnumSource(value = UserStatus.class, names = {"CREATED", "INACTIVATING"}, mode = EXCLUDE)
    void 활성화_실패__활성화_가능한_상태아님(UserStatus status) {
        user.setStatus(status);

        assertThatIllegalStateException()
                .isThrownBy(() -> user.activate(user.getCertCode(), OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES).plusSeconds(10)));
    }

    @Test
    void 활성화_실패__이메일_인증_시간_초과() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> user.activate(user.getCertCode(), OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES - 1)));
    }

    @Test
    void 활성화_실패__인증코드_불일치() {
        assertThatMapleBossException(INVALID_CERT_CODE)
                .isThrownBy(() -> user.activate("FAKE_CODE", OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES).plusSeconds(10)));
    }

    @Test
    void 비활성화_준비_성공() {
        user.setStatus(ACTIVATED);

        user.prepareInactivate();

        assertThat(user.getStatus()).isEqualTo(INACTIVATING);
    }

    @Test
    void 재활성화_성공() {
        user.setStatus(INACTIVATING);

        user.prepareCertCode(() -> "REACTIVATE_CODE");

        user.reActivate("REACTIVATE_CODE");

        assertThat(user.getStatus()).isEqualTo(ACTIVATED);
    }


    @ParameterizedTest
    @EnumSource(value = UserStatus.class, names = {"CREATED", "INACTIVATING"}, mode = EXCLUDE)
    void 재활성화_실패__재활성화_가능한_상태아님(UserStatus status) {
        user.setStatus(status);

        user.prepareCertCode(() -> "REACTIVATE_CODE");

        assertThatIllegalStateException().isThrownBy(() -> user.reActivate("REACTIVATE_CODE"));
    }

    @Test
    void 재활성화_실패__인증코드_불일치() {
        user.setStatus(INACTIVATING);

        user.prepareCertCode(() -> "REACTIVATE_CODE");

        assertThatMapleBossException(INVALID_CERT_CODE).isThrownBy(() -> user.reActivate("FAKE_CODE"));
    }

    @Test
    void 비활성화_성공() {
        user.setStatus(INACTIVATING);

        user.inactivate();

        assertThat(user.getStatus()).isEqualTo(INACTIVATED);
    }

    @ParameterizedTest
    @EnumSource(value = UserStatus.class, names = {"INACTIVATING"}, mode = EXCLUDE)
    void 비활성화_실패__비활성화_가능한_상태아님(UserStatus status) {
        user.setStatus(status);

        assertThatIllegalStateException().isThrownBy(() -> user.inactivate());
    }

    @Test
    void 정보수정_성공() {
        user.setStatus(ACTIVATED);

        user.update("2", "쩌로2", "goyounha12@naver.com");

        assertThat(user.getPassword()).isEqualTo("2");
        assertThat(user.getNickname()).isEqualTo("쩌로2");
        assertThat(user.getEmail()).isEqualTo("goyounha12@naver.com");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 정보수정_실패__비밀번호가_null_이거나_빈값(String password) {
        user.setStatus(ACTIVATED);

        assertThatIllegalArgumentException().isThrownBy(() -> user.update(password, "쩌로2", "goyounha12@naver.com"));
    }

    @Test
    void 정보수정_실패__비밀번호_공백() {
        user.setStatus(ACTIVATED);

        assertThatIllegalArgumentException().isThrownBy(() -> user.update(" ", "쩌로2", "goyounha12@naver.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 정보수정_실패__닉네임이_null_이거나_빈값(String nickname) {
        user.setStatus(ACTIVATED);

        assertThatIllegalArgumentException().isThrownBy(() -> user.update("2", nickname, "goyounha12@naver.com"));
    }

    @Test
    void 정보수정_실패__닉네임_공백() {
        user.setStatus(ACTIVATED);

        assertThatIllegalArgumentException().isThrownBy(() -> user.update("2", " ", "goyounha12@naver.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 정보수정_실패__이메일이_null_이거나_빈값(String email) {
        user.setStatus(ACTIVATED);

        assertThatIllegalArgumentException().isThrownBy(() -> user.update("2", "쩌로2", email));
    }

    @Test
    void 정보수정_실패__이메일_공백() {
        user.setStatus(ACTIVATED);

        assertThatIllegalArgumentException().isThrownBy(() -> user.update("2", "쩌로2", " "));
    }

    @ParameterizedTest
    @EnumSource(value = UserStatus.class, names = {"ACTIVATED"}, mode = EXCLUDE)
    void 정보수정_실패__정보수정_가능한_상태아님(UserStatus status) {
        user.setStatus(status);

        assertThatIllegalStateException().isThrownBy(() -> user.update("2", "쩌로2", "goyounha12@naver.com"));
    }

    @Test
    void 비밀번호_수정_성공() {
        user.setStatus(ACTIVATED);

        user.changePassword("2");

        assertThat(user.getPassword()).isEqualTo("2");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 비밀번호_수정_실패__비밀번호가_null_이거나_빈값(String password) {
        user.setStatus(ACTIVATED);

        assertThatIllegalArgumentException().isThrownBy(() -> user.changePassword(password));
    }

    @ParameterizedTest
    @EnumSource(value = UserStatus.class, names = {"ACTIVATED"}, mode = EXCLUDE)
    void 비밀번호_수정_실패__비밀번호_수정_가능한_상태아님(UserStatus status) {
        user.setStatus(status);

        assertThatIllegalStateException().isThrownBy(() -> user.changePassword("2"));
    }

    @Test
    void 비밀번호_수정_실패__새로운_비밀번호가_기존_비밀번호와_같음() {
        user.setStatus(ACTIVATED);

        assertThatMapleBossException(ALREADY_USED_PASSWORD).isThrownBy(() -> user.changePassword("1"));
    }
}
