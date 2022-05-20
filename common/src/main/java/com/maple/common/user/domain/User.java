package com.maple.common.user.domain;

import com.maple.common.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.time.OffsetDateTime;

import static com.maple.common.user.domain.UserStatus.*;
import static com.maple.core.exception.ErrorCode.ALREADY_USED_PASSWORD;
import static com.maple.core.exception.ErrorCode.INVALID_CERT_CODE;
import static com.maple.core.exception.MapleBossException.validate;
import static com.maple.core.exception.Preconditions.*;

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
    public String certCode;

    /* 상태 */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    public UserStatus status = UserStatus.CREATED;

    /* 인증코드 전송일 */
    public OffsetDateTime certCodeSentAt;

    /* 생성일 */
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public static final int CERTIFICATE_MINUTES = 5;

    public User(String loginId, String password, String nickname, String email) {
        require(Strings.isNotBlank(loginId));
        require(Strings.isNotBlank(password));
        require(Strings.isNotBlank(nickname));
        require(Strings.isNotBlank(email));

        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public void prepareCertCode(CertCodeGenerator certCodeGenerator) {
        this.certCodeSentAt = OffsetDateTime.now();
        this.certCode = certCodeGenerator.generate();
    }

    public void activate(String certCode, OffsetDateTime currentTime) {
        notNull(certCode);

        require(this.certCodeSentAt.plusMinutes(CERTIFICATE_MINUTES).isBefore(currentTime));

        check(CAN_MOVE_TO_ACTIVATED.contains(this.status));

        validate(this.certCode.equals(certCode), INVALID_CERT_CODE);

        this.status = ACTIVATED;
    }

    public void prepareInactivate(String certCode, OffsetDateTime currentTime) {
        require(Strings.isNotBlank(certCode));
        require(this.certCodeSentAt.plusMinutes(CERTIFICATE_MINUTES).isBefore(currentTime));

        check(CAN_MOVE_TO_INACTIVATING.contains(this.status));

        validate(this.certCode.equals(certCode), INVALID_CERT_CODE);

        this.status = INACTIVATING;
    }

    public void reActivate(String certCode) {
        check(CAN_MOVE_TO_ACTIVATED.contains(this.status));

        validate(certCode.equals(this.certCode), INVALID_CERT_CODE);

        this.status = ACTIVATED;
    }

    public void inactivate() {
        check(CAN_MOVE_TO_INACTIVATED.contains(this.status));

        this.status = INACTIVATED;
    }

    public void update(String password, String nickname, String email) {
        require(Strings.isNotBlank(password));
        require(Strings.isNotBlank(nickname));
        require(Strings.isNotBlank(email));

        check(this.status == ACTIVATED);

        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public void changePassword(String password) {
        require(Strings.isNotBlank(password));

        check(this.status == ACTIVATED);

        validate(!this.password.equals(password), ALREADY_USED_PASSWORD);

        this.password = password;
    }
}
