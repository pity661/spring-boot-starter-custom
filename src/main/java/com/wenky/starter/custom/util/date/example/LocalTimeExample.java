package com.wenky.starter.custom.util.date.example;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @program: spring-boot-starter-custom
 * @description: 1 HH:mm:ss
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-02 13:21
 */
public class LocalTimeExample {
  public static void main(String[] args) {
    //        localTimeTest();
    //    localTimeInitTest();
    localTimeFormatTest();
  }

  private static void localTimeFormatTest() {
    // ???
    DateTimeFormatter germanFormatter = DateTimeFormatter.ofPattern("HH:mm");
    // 按照local的默认习惯格式化
    //                        .withLocale(Locale.CHINA);
    LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
    // 13:37
    System.out.println(leetTime);

    // yyyy-MM-dd hh:mm:ss 12小时
    // yyyy-MM-dd HH:mm:ss 24小时
    DateTimeFormatter c12 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // now:2021-07-02
    System.out.println("now:" + c12.format(LocalDateTime.now()));

    LocalDate localDate = LocalDate.parse("2019-12-07", c12);
    // localDate:2019-12-07
    System.out.println("localDate:" + localDate);

    LocalDateTime localDateTime =
        LocalDateTime.parse(
            "2019-12-07 11:12:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    // localDateTime:2019-12-07T11:12:11
    System.out.println("localDateTime:" + localDateTime);
    // 2019-12-07 11:12:11
    System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(localDateTime));
    // 2019-12-07T11:12:11
    System.out.println(DateTimeFormatter.ISO_DATE_TIME.format(localDateTime));
    // 11:12:11
    System.out.println(DateTimeFormatter.ISO_LOCAL_TIME.format(localDateTime));
    // 2019-12-07
    System.out.println(DateTimeFormatter.ISO_DATE.format(localDateTime));
    // 11:12:11
    System.out.println(DateTimeFormatter.ISO_TIME.format(localDateTime));
  }

  private static void localTimeInitTest() {
    LocalTime late = LocalTime.of(23, 59, 59);
    // 23:59:59
    System.out.println(late);
  }

  private static void localTimeTest() {
    ZoneId zone1 = ZoneId.of("Europe/Berlin");
    ZoneId zone2 = ZoneId.of("Brazil/East");
    LocalTime now1 = LocalTime.now(zone1);
    LocalTime now2 = LocalTime.now(zone2);
    LocalTime now = LocalTime.now();

    // false
    System.out.println(now1.isBefore(now2));

    // calculate
    long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
    long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

    System.out.println(hoursBetween);
    System.out.println(minutesBetween);
  }
}
