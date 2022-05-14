package com.maple.api.service;

import com.maple.api.service.dto.UserCreateDto;
import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserAppService implements UserAppService {

    private final UserService userService;

    private final CertCodeGenerator certCodeGenerator;

    @Override
    public User create(UserCreateDto dto) {
        val user = new User(dto.loginId(), dto.password(), dto.nickname(), dto.email(), certCodeGenerator);

        return userService.create(user);
    }
}
