package com.github.marcopollivier.avenuecode.productmanager.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public final class DateUtil {

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();
    private static final DateTimeFormatter FORMATTER = ofPattern("yyyy-MM-dd").withZone(ZONE_ID);

    private static final DateTimeFormatter TIME_FORMATTER = ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZONE_ID);

    /**
     * private constructor
     */
    private DateUtil() {// prevent instantiation
    }

    public static String toString(LocalDate date) {
        return date.format(FORMATTER);
    }

    public static String toString(LocalDateTime date) {
        return date.format(TIME_FORMATTER);
    }

    public static String toString(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate toDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public static LocalDateTime toDateTime(String date) {
        return LocalDateTime.parse(date, TIME_FORMATTER);
    }

    public static LocalDate toDate(String date, String pattern) {
        return LocalDate.parse(date, ofPattern(pattern).withZone(ZONE_ID));
    }

    public static LocalDate toLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

}
