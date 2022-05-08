package com.maple.admin.eventlistener;

import com.maple.common.notification.event.UnhandledExceptionEvent;
import com.maple.integration.slack.SlackApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExceptionEventListener {

    private final SlackApi slackApi;

    @EventListener(UnhandledExceptionEvent.class)
    public void onUnhandledExceptionEvent(UnhandledExceptionEvent event) {
        slackApi.chatPostMessage(event.getBotName(), event.getChannel(), event.getMessage());
    }
}
