package com.maple.api.controller;

import com.maple.common.notification.event.UnhandledExceptionEvent;
import com.maple.core.exception.MapleBossException;
import com.maple.integration.slack.SlackProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Profile({"dev", "prod"})
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiAdvice {

    private final MessageSource messageSource;
    private final SlackProperties slackProperties;
    private final ApplicationEventPublisher eventPublisher;

    @ExceptionHandler(Exception.class)
    public ErrorResponse exception(Exception ex) {
        log.error(ex.getMessage(), ex);

        eventPublisher.publishEvent(new UnhandledExceptionEvent("Maple Boss", slackProperties.getLogChannel(), ex));

        return new ErrorResponse(new ErrorData("예상치 못한 에러가 발생했습니다."));
    }

    @ExceptionHandler(MapleBossException.class)
    public ErrorResponse mapleBossException(MapleBossException ex) {
        log.error(ex.getMessage(), ex);

        val message = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());

        return new ErrorResponse(new ErrorData(message));
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorResponse {
        private ErrorData error;
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorData {
        private String message;
    }
}
