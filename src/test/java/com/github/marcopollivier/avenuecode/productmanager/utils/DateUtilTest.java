package com.github.marcopollivier.avenuecode.productmanager.utils;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {

    @Test
    public void localDateToStringTest() throws Exception {
        String retorno = DateUtil.toString(LocalDate.of(2017, 11, 12));
        assertEquals("2017-11-12", retorno);
    }

    @Test
    public void localDateTimeToStringTest() throws Exception {
        String retorno = DateUtil.toString(LocalDateTime.of(2017, 11, 12, 10, 59, 11));
        assertEquals("2017-11-12 10:59:11", retorno);
    }

    @Test
    public void toStringWithLocalDateAndPattern() throws Exception {
        String retorno = DateUtil.toString(LocalDate.of(2017, 11, 12), "ddMMyyyy");
        assertEquals("12112017", retorno);
    }

    @Test
    public void toDateTest() throws Exception {
        LocalDate retorno = DateUtil.toDate("2017-11-12");
        assertEquals(retorno.getDayOfMonth(), 12);
    }

    @Test
    public void toDateTimeTimeTest() throws Exception {
        LocalDateTime retorno = DateUtil.toDateTime("2017-11-12 10:59:11");
        assertEquals(retorno.getHour(), 10);
        assertEquals(retorno.getMinute(), 59);
        assertEquals(retorno.getSecond(), 11);
    }

    @Test
    public void toDateWithStringAndPattern() throws Exception {
        LocalDate retorno = DateUtil.toDate("12112017", "ddMMyyyy");
        assertEquals(retorno.getDayOfMonth(), 12);
    }

    @Test (expected = DateTimeParseException.class)
    public void toDateWithStringAndCompletePattern() throws Exception {
        LocalDate retorno = DateUtil.toDate("2017-06-07", "yyyy-MM-dd HH:mm:ss");
        assertEquals(retorno.getDayOfMonth(), 06);
    }

    @Test
    public void toLocalDateTest() {
        LocalDateTime localDateTime = DateUtil.toDateTime("2017-11-12 10:59:11");
        LocalDate retorno = DateUtil.toLocalDate(localDateTime);
        assertEquals(retorno.getDayOfMonth(), 12);
    }

}