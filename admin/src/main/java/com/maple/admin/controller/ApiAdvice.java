package com.maple.admin.controller;

import com.maple.common.exception.MapleBossException;
import com.maple.common.notification.event.UnhandledExceptionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiAdvice {

    private final MessageSource messageSource;
    private final ApplicationEventPublisher eventPublisher;

    public static final String LOG_CHANNEL = "C03BKF0CYL8";
    public static final String BOT_NAME = "Maple Boss Admin";

    @ExceptionHandler(MapleBossException.class)
    public ErrorResponse mapleBossException(MapleBossException ex) {
        log.error(ex.getMessage(), ex);

        val message = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());

        return new ErrorResponse(new ErrorData(message));
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse runtimeException(RuntimeException ex) {
        log.error(ex.getMessage(), ex);

        eventPublisher.publishEvent(new UnhandledExceptionEvent(BOT_NAME, LOG_CHANNEL, ex));

        return new ErrorResponse(new ErrorData("예상치 못한 에러가 발생했습니다."));
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse exception(Exception ex) {
        log.error(ex.getMessage(), ex);

        eventPublisher.publishEvent(new UnhandledExceptionEvent(BOT_NAME, LOG_CHANNEL, ex));

        return new ErrorResponse(new ErrorData("예상치 못한 에러가 발생했습니다."));
    }

    public record ErrorResponse(ErrorData error) {
    }

    public record ErrorData(String message) {
    }
}
