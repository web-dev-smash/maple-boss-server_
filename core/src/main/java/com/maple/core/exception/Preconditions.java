package com.maple.core.exception;

import java.text.MessageFormat;
import java.util.Objects;

public class Preconditions {

    public static <T> T notNull(T obj) {
        return Objects.requireNonNull(obj);
    }

    public static void require(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void require(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * e.g.) require(condition, "message {0} {1}", arg0, arg1)
     */
    public static void require(boolean expression, String message, Object... args) {
        if (!expression) {
            throw new IllegalArgumentException(MessageFormat.format(message, args));
        }
    }

    public static void check(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void check(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * e.g.) require(condition, "message {0} {1}", arg0, arg1)
     */
    public static void check(boolean expression, String message, Object... args) {
        if (!expression) {
            throw new IllegalStateException(MessageFormat.format(message, args));
        }
    }

    public static void validate(boolean expression, ErrorCode errorCode) {
        if (!expression) {
            throw new MapleBossException(errorCode);
        }
    }
}
