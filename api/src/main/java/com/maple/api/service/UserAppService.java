package com.maple.api.service;

import com.maple.api.service.dto.UserCreateDto;
import com.maple.api.service.dto.UserPrepareWithdrawalDto;
import com.maple.common.user.domain.User;

public interface UserAppService {

    /**
     * 유저 생성
     */
    User create(UserCreateDto dto);

    /**
     * 인증코드 요청
     */
    void requestCertCode(long id);

    /**
     * 비활성화 준비
     */
    void prepareWithdrawal(UserPrepareWithdrawalDto dto);
}
