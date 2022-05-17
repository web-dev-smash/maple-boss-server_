package com.maple.api.eventlistener;

import com.maple.common.user.domain.UserCertCodeRequestEvent;
import com.maple.common.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailEventListener {

    private final UserRepository userRepository;

    @EventListener(UserCertCodeRequestEvent.class)
    public void onUserCertCodeRequest(UserCertCodeRequestEvent event) {
        val user = userRepository.findById(event.getId()).orElseThrow();

        // TODO: 2022/05/16 이메일 서비스 연결 필요
    }
}
