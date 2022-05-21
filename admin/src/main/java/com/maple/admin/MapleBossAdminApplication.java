package com.maple.admin;

import com.maple.common.notification.event.SlackEvent;
import com.maple.integration.slack.SlackProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@SpringBootApplication
public class MapleBossAdminApplication {

    private final SlackProperties slackProperties;
    private final ApplicationEventPublisher eventPublisher;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        eventPublisher.publishEvent(new SlackEvent("Maple Boss", slackProperties.getLogChannel(), "Admin Started"));
    }

    public static void main(String[] args) {
        SpringApplication.run(MapleBossAdminApplication.class, args);
    }
}
