package com.wenky.starter.custom.util.date.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * @program: spring-boot-starter-custom
 * @description: 2 yyyy-MM-dd
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-02 15:42
 */
public class LocalDateExample {
  public static void main(String[] args) {
    //        localDateTest();
    localDateFormatTest();
  }

  private static void localDateFormatTest() {
    DateTimeFormatter germanFormatter =
        DateTimeFormatter.ofPattern("dd.MM.yyyy").withLocale(Locale.CHINA);

    LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
    System.out.println(xmas); // 2014-12-24
  }

  private static void localDateTest() {
    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
    LocalDate yesterday = tomorrow.minusDays(2);

    LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
    DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
    System.out.println(dayOfWeek); // FRIDAY
  }
}
