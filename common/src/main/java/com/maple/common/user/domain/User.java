package com.maple.common.user.domain;

import com.maple.common.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * 회원
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_login_id", columnNames = "login_id"),
                @UniqueConstraint(name = "uq_email", columnNames = "email")
        }
)
public class User extends BaseEntity {

    /* 로그인 아이디 */
    @Column(length = 15)
    private String loginId;

    /* 비밀번호 */
    @Column(length = 100)
    private String password;

    /* 이름 */
    @Column(length = 30)
    private String name;

    /* 닉네임 */
    @Column(length = 7)
    private String nickname;

    /* 이메일 */
    @Column(length = 30)
    private String email;

    /* 상태 */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserStatus status = UserStatus.CREATED;

    /* 생성일 */
    private OffsetDateTime createAt = OffsetDateTime.now();

    public User(String loginId, String password, String name, String nickname, String email) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }
}
