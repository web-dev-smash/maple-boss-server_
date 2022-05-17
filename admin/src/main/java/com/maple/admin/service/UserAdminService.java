package com.maple.admin.service;

import com.maple.common.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAdminService {

    /**
     * 전체 유저 목록 조회
     */
    Page<User> getAllUser(Pageable pageable);
}
