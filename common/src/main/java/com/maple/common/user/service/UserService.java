package com.maple.common.user.service;

import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;

import java.time.OffsetDateTime;

public interface UserService {

    /**
     * 사용자 생성
     * <p>
     * 회원가입 신청만 상태이고, 인증을 해야 사용 가능하다.
     * status = CREATED
     */
    User create(User member);

    /**
     * 사용자 활성화
     * <p>
     * 인증에 성공하면, 사용가능
     * status = ACTIVATED
     * <p>
     * 인증코드가 다르면,
     * INVALID_CERT_CODE
     */
    void activate(long id, OffsetDateTime currentTime, CertCodeGenerator codeGenerator);

    /**
     * 사용자 비활성화 준비
     * <p>
     * 활성화 혹은 비활성화로 변경 가능하게 됨
     * status = INACTIVATING
     */
    void prepareWithdrawal(long id);
}
