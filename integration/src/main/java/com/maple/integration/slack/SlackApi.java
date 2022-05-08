package com.maple.integration.slack;

import com.slack.api.methods.MethodsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SlackApi {

    private final MethodsClient slackClient;

    public void chatPostMessage(String botName, String channel, String message) {
        try {
            slackClient.chatPostMessage(builder -> builder
                    .username(botName)
                    .channel(channel)
                    .text(message)
            );
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
