package com.wenky.starter.custom.util.date.example;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * @program: spring-boot-starter-custom
 * @description: 3 yyyy-MM-dd HH:mm:ss
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-03 10:28
 */
public class LocalDateTimeExample {
  public static void main(String[] args) {
    //      localDateTimeTest();
    //      localDateTime2DateTest();
    localDateTimeFormatTest();
  }

  private static void localDateTimeFormatTest() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime localDateTime = LocalDateTime.parse("2021-01-01 11:10:00", formatter);
    System.out.println(localDateTime);
    System.out.println(formatter.format(localDateTime));
  }

  private static void localDateTime2DateTest() {
    LocalDateTime localDateTime = LocalDateTime.now();
    Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    System.out.println(date);
  }

  private static void localDateTimeTest() {
    // 初始化localDateTime
    LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
    // 星期几
    DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
    System.out.println(dayOfWeek); // WEDNESDAY
    // 几月份
    Month month = sylvester.getMonth();
    System.out.println(month); // DECEMBER
    // 当天的第几分钟
    long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
    System.out.println(minuteOfDay); // 1439
  }
}
