package com.example.camunda_demo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {
    public static final String CAMUNDA_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final DateTimeFormatter CAMUNDA_DATE_FORMATTER = DateTimeFormatter.ofPattern(CAMUNDA_DATE_FORMAT);

    public static String nextRunDate(Long delayMinutes) {
        if (delayMinutes == null) {
            return null;
        }

        LocalDateTime next = LocalDateTime.now().plusMinutes(delayMinutes);
        return CAMUNDA_DATE_FORMATTER.format(next);
    }
}
