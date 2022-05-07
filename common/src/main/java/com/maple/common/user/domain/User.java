package com.maple.common.user.domain;

import com.maple.common.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.time.OffsetDateTime;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 회원
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_login_id", columnNames = "loginId"),
                @UniqueConstraint(name = "uq_email", columnNames = "email")
        }
)
public class User extends BaseEntity {

    /* 로그인 아이디 */
    @Column(length = 15 , nullable = false)
    private String loginId;

    /* 비밀번호 */
    @Column(length = 100 , nullable = false)
    private String password;

    /* 이름 */
    @Column(length = 30 , nullable = false)
    private String name;

    /* 닉네임 */
    @Column(length = 7 , nullable = false)
    private String nickname;

    /* 이메일 */
    @Column(length = 30 , nullable = false)
    private String email;

    /* 상태 */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserStatus status = UserStatus.CREATED;

    /* 생성일 */
    private OffsetDateTime createAt = OffsetDateTime.now();

    public User(String loginId, String password, String name, String nickname, String email) {
        checkArgument(Strings.isNotBlank(loginId), "로그인아이디는 필수입니다.");
        checkArgument(Strings.isNotBlank(password), "비밀번호는 필수입니다.");
        checkArgument(Strings.isNotBlank(name), "이름은 필수입니다.");
        checkArgument(Strings.isNotBlank(nickname), "닉네임은 필수입니다.");
        checkArgument(Strings.isNotBlank(email), "이메일은 필수입니다.");

        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }
}
