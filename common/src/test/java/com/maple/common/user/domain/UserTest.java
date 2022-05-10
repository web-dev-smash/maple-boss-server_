package com.maple.common.user.domain;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.OffsetDateTime;

import static com.maple.common.exception.ErrorCode.ALREADY_USED_PASSWORD;
import static com.maple.common.fixture.UserFixture.createUser;
import static com.maple.common.support.MapleBossExceptionTest.assertThatMapleBossException;
import static com.maple.common.user.domain.User.CERTIFICATE_MINUTES;
import static com.maple.common.user.domain.UserStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class UserTest {

    private MockCertCodeGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new MockCertCodeGenerator();
    }

    @Test
    void 유저_생성_성공() {
        val user = new User("goyounha11", "1", "쩌로", "goyounha11@naver.com", generator);

        assertThat(user.getLoginId()).isEqualTo("goyounha11");
        assertThat(user.getPassword()).isEqualTo("1");
        assertThat(user.getNickname()).isEqualTo("쩌로");
        assertThat(user.getEmail()).isEqualTo("goyounha11@naver.com");
        assertThat(user.getStatus()).isEqualTo(CREATED);
        assertThat(user.getCreateAt()).isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_로그인아이디가_null_이거나_빈값이면_실패(String loginId) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User(loginId, "1", "쩌로", "goyounha11@naver.com", generator));
    }

    @Test
    void 유저_로그인아이디가_공백이면_실패() {
        val loginId = "     ";
        assertThatIllegalArgumentException().isThrownBy(() -> new User(loginId, "1", "쩌로", "goyounha11@naver.com", generator));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_비밀번호가_null_이거나_빈값이면_실패(String password) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", password, "쩌로", "goyounha11@naver.com", generator));
    }

    @Test
    void 유저_비밀번호가_공백이면_실패() {
        val password = "     ";
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", password, "쩌로", "goyounha11@naver.com", generator));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_닉네임이_null_이거나_빈값이면_실패(String nickname) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", "1", nickname, "goyounha11@naver.com", generator));
    }

    @Test
    void 유저_닉네임이_공백이면_실패() {
        val nickname = "     ";
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", "1", nickname, "goyounha11@naver.com", generator));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_이메일이_null_이거나_빈값이면_실패(String email) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", "1", "쩌로", email, generator));
    }

    @Test
    void 유저_이메일이_공백이면_실패() {
        val email = "     ";
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", "1", "쩌로", email, generator));
    }

    @Test
    void 정보_수정_성공() {
        val user = createUser();

        user.update("2", "쩌로2", "goyounha12@naver.com");

        assertThat(user.getPassword()).isEqualTo("2");
        assertThat(user.getNickname()).isEqualTo("쩌로2");
        assertThat(user.getEmail()).isEqualTo("goyounha12@naver.com");
    }

    @Test
    void 정보수정시_비밀번호가_공백이면_실패() {
        val user = createUser();

        assertThatIllegalArgumentException().isThrownBy(() -> user.update(" ", "쩌로2", "goyounha12@naver.com"));
    }

    @Test
    void 정보수정시_닉네임이_공백이면_실패() {
        val user = createUser();

        assertThatIllegalArgumentException().isThrownBy(() -> user.update("2", " ", "goyounha12@naver.com"));
    }

    @Test
    void 정보수정시_이메일이_공백이면_실패() {
        val user = createUser();

        assertThatIllegalArgumentException().isThrownBy(() -> user.update("2", "쩌로2", " "));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 정보수정시_패스워드가_null_이거나_빈값_이면_실패(String password) {
        val user = createUser();

        assertThatIllegalArgumentException().isThrownBy(() -> user.update(password, "쩌로2", "goyounha12@naver.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 정보수정시_닉네임이_null_이거나_빈값_이면_실패(String nickname) {
        val user = createUser();

        assertThatIllegalArgumentException().isThrownBy(() -> user.update("2", nickname, "goyounha12@naver.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 정보수정시_이메일이_null_이거나_빈값_이면_실패(String email) {
        val user = createUser();

        assertThatIllegalArgumentException().isThrownBy(() -> user.update("2", "쩌로2", email));
    }

    @Test
    void 패스워드_수정_성공() {
        val user = createUser();

        user.changePassword("2");

        assertThat(user.getPassword()).isEqualTo("2");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 패스워드_수정시_패스워드가_null_이거나_빈값_이면_실패(String password) {
        val user = createUser();

        user.changePassword("2");
    }

    @Test
    void 패스워드_수정시_새로운_패스워드가_기존_패스워드와_같으면_실패() {
        val user = createUser();

        assertThatMapleBossException(ALREADY_USED_PASSWORD).isThrownBy(() -> user.changePassword("1"));
    }

    @Test
    void 활성화_성공() {
        val user = createUser();

        user.activate(OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES + 1));

        assertThat(user.getStatus()).isEqualTo(ACTIVATED);
    }

    @Test
    void 활성화시_이메일_인증_시간_초과하면_실패() {
        val user = createUser();

        assertThatIllegalArgumentException().isThrownBy(() -> user.activate(OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES - 1)));
    }

    @Test
    void 비활성화_준비_성공() {
        val user = createUser();

        user.activate(OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES + 1));

        user.prepareInactivate();

        assertThat(user.getStatus()).isEqualTo(INACTIVATING);
    }

    @Test
    void 비활성화_준비_상태에서_비활성화_시도시_성공() {
        val user = createUser();

        user.activate(OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES + 1));

        user.prepareInactivate();

        user.inactivate();

        assertThat(user.getStatus()).isEqualTo(INACTIVATED);
    }

    @Test
    void 비활성화_준비_상태에서_활성화_시도시_성공() {
        val user = createUser();

        user.activate(OffsetDateTime.now().plusMinutes(CERTIFICATE_MINUTES + 1));

        user.prepareInactivate();

        user.reActivate(generator);

        assertThat(user.getStatus()).isEqualTo(ACTIVATED);
    }
}
