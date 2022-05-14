package com.maple.common.user.domain;

import com.maple.common.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.time.OffsetDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.maple.common.exception.ErrorCode.ALREADY_USED_PASSWORD;
import static com.maple.common.exception.MapleBossException.validate;
import static com.maple.common.user.domain.UserStatus.*;

/**
 * 유저
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
    @Column(length = 15, nullable = false)
    private String loginId;

    /* 비밀번호 */
    @Column(length = 100, nullable = false)
    private String password;

    /* 닉네임 */
    @Column(length = 7, nullable = false)
    private String nickname;

    /* 이메일 */
    @Column(length = 30, nullable = false)
    private String email;

    /* 인증코드 */
    @Column(length = 30, nullable = false)
    private String certCode;

    /* 상태 */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserStatus status = UserStatus.CREATED;

    /* 생성일 */
    private OffsetDateTime createAt = OffsetDateTime.now();

    public static final int CERTIFICATE_MINUTES = 5;

    public User(String loginId, String password, String nickname, String email, CertCodeGenerator codeGenerator) {
        checkArgument(Strings.isNotBlank(loginId));
        checkArgument(Strings.isNotBlank(password));
        checkArgument(Strings.isNotBlank(nickname));
        checkArgument(Strings.isNotBlank(email));

        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.certCode = codeGenerator.generate();
    }

    public void activate(OffsetDateTime currentTime) {
        checkArgument(this.createAt.plusMinutes(CERTIFICATE_MINUTES).isBefore(currentTime));

        checkState(CAN_MOVE_TO_ACTIVATED.contains(this.status));

        this.status = ACTIVATED;
    }

    public void prepareInactivate() {
        checkState(CAN_MOVE_TO_INACTIVATING.contains(this.status));

        this.status = INACTIVATING;
    }

    public void reActivate(CertCodeGenerator codeGenerator) {
        checkState(CAN_MOVE_TO_ACTIVATED.contains(this.status));

        this.status = ACTIVATED;
        this.certCode = codeGenerator.generate();
    }

    public void inactivate() {
        checkState(CAN_MOVE_TO_INACTIVATED.contains(this.status));

        this.status = INACTIVATED;
    }

    public void update(String password, String nickname, String email) {
        checkArgument(Strings.isNotBlank(password));
        checkArgument(Strings.isNotBlank(nickname));
        checkArgument(Strings.isNotBlank(email));

        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public void changePassword(String password) {
        checkArgument(Strings.isNotBlank(password));

        validate(!this.password.equals(password), ALREADY_USED_PASSWORD);

        this.password = password;
    }
}
