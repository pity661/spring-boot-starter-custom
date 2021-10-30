package com.wenky.starter.custom.util.date.example;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-06-28 19:54
 */
public class ClockExample {
  public static void main(String[] args) {
    clockTest();
    //    zoneIdTest();
  }

  private static void zoneIdTest() {
    // all zoneId information
    //    System.out.println(ZoneId.getAvailableZoneIds());

    ZoneId zoneId = ZoneId.of("Asia/Shanghai");
    System.out.println(zoneId.getRules());
  }

  private static void clockTest() {
    Clock clock = Clock.systemDefaultZone();
    long millis = clock.millis();
    System.out.println(millis);
    // same as
    System.out.println(System.currentTimeMillis());

    Instant instant = clock.instant();
    Date legacyDate = Date.from(instant);
    System.out.println(legacyDate);
    // same as
    System.out.println(new Date());
  }
}
