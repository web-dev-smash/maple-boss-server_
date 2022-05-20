package com.maple.common.user.service;

import com.maple.common.user.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

import static com.maple.core.exception.ErrorCode.ALREADY_EXISTS_EMAIL;
import static com.maple.core.exception.ErrorCode.ALREADY_EXISTS_LOGIN_ID;
import static com.maple.core.exception.MapleBossException.validate;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainUserService implements UserService {

    private final UserRepository userRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public User create(User user, CertCodeGenerator certCodeGenerator) {
        validate(userRepository.findByLoginId(user.getLoginId()).isEmpty(), ALREADY_EXISTS_LOGIN_ID);
        validate(userRepository.findByEmail(user.getEmail()).isEmpty(), ALREADY_EXISTS_EMAIL);

        user.prepareCertCode(certCodeGenerator);
        user = userRepository.save(user);

        eventPublisher.publishEvent(new UserCreateEvent(user.getId()));

        return user;
    }

    @Override
    public void activate(long id, String certCode, OffsetDateTime currentTime) {
        val user = userRepository.findById(id).orElseThrow();

        user.activate(certCode, currentTime);

        eventPublisher.publishEvent(new UserActiveEvent(user.getId()));
    }

    @Override
    public void prepareInactivate(long id, String certCode) {
        val user = userRepository.findById(id).orElseThrow();

        user.prepareInactivate(certCode);
    }

    @Override
    public void requestCertCode(long id, CertCodeGenerator certCodeGenerator) {
        val user = userRepository.findById(id).orElseThrow();

        user.prepareCertCode(certCodeGenerator);

        eventPublisher.publishEvent(new UserCertCodeRequestEvent(user.getId()));
    }

    @Override
    public void reActivate(long id, String certCode) {
        val user = userRepository.findById(id).orElseThrow();

        user.reActivate(certCode);
    }
}
