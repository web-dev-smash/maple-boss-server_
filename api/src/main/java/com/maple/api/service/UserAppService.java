package com.maple.api.service;

import com.maple.api.service.dto.UserCreateDto;
import com.maple.common.user.domain.User;

public interface UserAppService {

    /**
     * 유저 생성
     */
    User create(UserCreateDto dto);
}
