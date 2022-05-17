package com.maple.common.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserActiveEvent {
    private long userId;
}
