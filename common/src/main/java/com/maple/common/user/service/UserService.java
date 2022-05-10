package com.maple.common.user.service;

import com.maple.common.user.domain.User;

public interface UserService {
    User createUser(User member);

    void activate(long id, String code);
}
