package com.maple.common.support;

import lombok.val;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Exceptions {
    private static final String START_WITH = "at ";

    private static final List<String> BLOCKED_PACKAGES_START_WITH = List.of(
            "org.spring", "java.base", "org.hibernate",
            "org.apache", "com.sun", "javax.servlet",
            "com.fasterxml.jackson", "jdk.internal",
            "io.netty", "reactor.core", "reactor.netty"
    );

    public static String simplifyMessage(Throwable throwable) {
        val sw = new StringWriter();

        throwable.printStackTrace(new PrintWriter(sw));

        return truncate(sw.toString());
    }

    private static String truncate(String messages) {
        return Arrays.stream(messages.split("\n"))
                .filter(it -> !(it.contains(START_WITH) && containsAny(it, BLOCKED_PACKAGES_START_WITH)))
                .collect(Collectors.joining("\n"));
    }

    private static boolean containsAny(String source, List<String> keywords) {
        for (String keyword : keywords) {
            if (source.contains(keyword)) {
                return true;
            }
        }

        return false;
    }
}

