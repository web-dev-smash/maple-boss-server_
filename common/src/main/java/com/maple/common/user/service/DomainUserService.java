package com.maple.common.user.service;

import com.maple.common.user.domain.*;
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
    public User create(User user) {
        user = userRepository.save(user);

        eventPublisher.publishEvent(new UserCreateEvent(user.getId()));

        return user;
    }

    @Override
    public void activate(long id, OffsetDateTime currentTime, CertCodeGenerator codeGenerator) {
        val user = userRepository.findById(id).orElseThrow();

        validate(codeGenerator.generate().equals(user.getCertCode()), INVALID_CERT_CODE);

        user.activate(currentTime);

        eventPublisher.publishEvent(new UserActiveEvent(user.getId()));
    }

    @Override
    public void prepareWithdrawal(long id) {
        val user = userRepository.findById(id).orElseThrow();

        user.prepareInactivate();
    }
}
