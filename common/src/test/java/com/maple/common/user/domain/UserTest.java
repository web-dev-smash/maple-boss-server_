package com.maple.common.user.domain;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static com.maple.common.user.domain.UserStatus.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class UserTest {
    
    @Test
    void 유저_생성_성공() {
        val user = new User("goyounha11", "1", "신철호", "쩌로", "goyounha11@naver.com");

        assertThat(user.getLoginId()).isEqualTo("goyounha11");
        assertThat(user.getPassword()).isEqualTo("1");
        assertThat(user.getName()).isEqualTo("신철호");
        assertThat(user.getNickname()).isEqualTo("쩌로");
        assertThat(user.getEmail()).isEqualTo("goyounha11@naver.com");
        assertThat(user.getStatus()).isEqualTo(CREATED);
        assertThat(user.getCreateAt()).isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_로그인아이디가_널이거나_빈값이면_실패(String loginId) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User(loginId, "1", "신철호", "쩌로", "goyounha11@naver.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_비밀번호가_널이거나_빈값이면_실패(String password) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", password, "신철호", "쩌로", "goyounha11@naver.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_이름이_널이거나_빈값이면_실패(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", "1", name, "쩌로", "goyounha11@naver.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_닉네임이_널이거나_빈값이면_실패(String nickname) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", "1", "신철호", nickname, "goyounha11@naver.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 유저_이메일이_널이거나_빈값이면_실패(String email) {
        assertThatIllegalArgumentException().isThrownBy(() -> new User("goyounha11", "1", "신철호", "쩌로", email));
    }

}
