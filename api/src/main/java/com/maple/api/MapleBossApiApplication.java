package com.maple.api;

import com.maple.common.notification.event.SlackEvent;
import com.maple.integration.slack.SlackProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@SpringBootApplication
@EnableConfigurationProperties(value = {SlackProperties.class})
public class MapleBossApiApplication {

    private final SlackProperties slackProperties;

    private final ApplicationEventPublisher eventPublisher;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        eventPublisher.publishEvent(new SlackEvent("Maple Boss Api", slackProperties.getLogChannel(), "API Started"));
    }

    public static void main(String[] args) {
        SpringApplication.run(MapleBossApiApplication.class, args);
    }
}
