package com.maple.common.user.service;

import com.maple.common.user.domain.User;
import com.maple.common.user.domain.UserActiveEvent;
import com.maple.common.user.domain.UserCreateEvent;
import com.maple.common.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

import static com.maple.common.exception.ErrorCode.INVALID_CERT_CODE;
import static com.maple.common.exception.MapleBossException.validate;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainUserService implements UserService {

    private final UserRepository userRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public User createUser(User user) {
        user = userRepository.save(user);

        eventPublisher.publishEvent(new UserCreateEvent(user.getId()));

        return user;
    }

    @Override
    public void activate(long id, String code) {
        val user = userRepository.findById(id).orElseThrow();

        validate(code.equals(user.getCertCode()), INVALID_CERT_CODE);

        user.activate(OffsetDateTime.now());

        eventPublisher.publishEvent(new UserActiveEvent(user.getId()));
    }
}
