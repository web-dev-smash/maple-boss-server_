package com.maple.common.notification.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SlackEvent {

    private String botName;
    private String channel;
    private String message;
}
