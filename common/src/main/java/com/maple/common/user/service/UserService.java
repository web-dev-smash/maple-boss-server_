package com.maple.common.user.service;

import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;

public interface UserService {

    /**
     * 사용자 생성
     * <p>
     * 유저가입 신청만 상태이고, 인증을 해야 사용 가능하다.
     * <p>
     * status = CREATED
     * <p>
     * 이미 사용중인 로그인 아이디가 있으면, ALREADY_EXISTS_LOGIN_ID
     * <p>
     * 이미 사용중인 이메일이 있으면, ALREADY_EXISTS_EMAIL
     */
    User create(User member, CertCodeGenerator certCodeGenerator);

    /**
     * 사용자 활성화
     * <p>
     * 인증에 성공하면, 사용가능
     * <p>
     * status = ACTIVATED
     * <p>
     * 인증코드가 다르면, INVALID_CERT_CODE
     */
    void activate(long id, String certCode);

    /**
     * 사용자 비활성화 준비
     * <p>
     * 활성화 혹은 비활성화로 변경 가능하게 됨
     * <p>
     * status = INACTIVATING
     */
    void prepareInactivate(long id, String certCode);

    /**
     * 인증코드 요청
     */
    void requestCertCode(long id, CertCodeGenerator certCodeGenerator);

    /**
     * 사용자 재활성화
     * <p>
     * 인증에 성공하면, 사용가능
     * <p>
     * status = ACTIVATED
     * <p>
     * 인증코드가 다르면, INVALID_CERT_CODE
     */
    void reActivate(long id, String certCode);
}
