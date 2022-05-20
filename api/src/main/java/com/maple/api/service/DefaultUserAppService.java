package com.maple.api.service;

import com.maple.api.service.dto.UserCreateDto;
import com.maple.api.service.dto.UserPrepareWithdrawalDto;
import com.maple.common.user.domain.CertCodeGenerator;
import com.maple.common.user.domain.User;
import com.maple.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.maple.core.exception.Preconditions.notNull;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserAppService implements UserAppService {

    private final UserService userService;

    private final CertCodeGenerator certCodeGenerator;

    @Override
    public User create(UserCreateDto dto) {
        notNull(dto);

        val user = new User(dto.getLoginId(), dto.getPassword(), dto.getNickname(), dto.getEmail());

        return userService.create(user, certCodeGenerator);
    }

    @Override
    public void requestCertCode(long id) {
        userService.requestCertCode(id, certCodeGenerator);
    }

    @Override
    public void prepareWithdrawal(UserPrepareWithdrawalDto dto) {
        userService.prepareInactivate(dto.getId(), dto.getCertCode());
    }
}
