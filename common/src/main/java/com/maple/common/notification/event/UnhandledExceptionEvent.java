package com.maple.common.notification.event;

import com.maple.common.support.Exceptions;

public class UnhandledExceptionEvent extends SlackEvent {

    public UnhandledExceptionEvent(String botName, String channel, Exception ex) {
        super(botName, channel, """
                ```<!channel> {message}```
                """.replace("{message}", Exceptions.simplifyMessage(ex))
        );
    }
}
