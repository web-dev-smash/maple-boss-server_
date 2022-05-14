package com.maple.common.user.service;

import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;

import java.time.OffsetDateTime;

public interface UserService {
    User create(User member);

    void activate(long id, OffsetDateTime currentTime, CertCodeGenerator codeGenerator);
}
