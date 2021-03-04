package com.wenky.starter.custom.util.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-25 10:15
 */
public class CalculateDateUtils {
  // 当前时间加上指定天数
  public static Date addDays(Integer amount) {
    return addDays(new Date(), amount);
  }
  // 加上指定天数
  public static Date addDays(Date date, Integer amount) {
    return DateUtils.addDays(date, amount);
  }

  public static Date addMinutes(Date date, Integer amount) {
    return calculate(date, amount, ChronoUnit.MINUTES);
  }

  public static Date addSeconds(Date date, Integer amount) {
    return calculate(date, amount, ChronoUnit.SECONDS);
  }
  // 当前时间加上指定时间间隔
  private static Date calculate(Date date, Integer amount, ChronoUnit chronoUnit) {
    LocalDateTime localDateTime = DateTransformUtils.date2LocalDateTime(date);
    localDateTime = localDateTime.plus(amount, chronoUnit);
    return DateTransformUtils.localDateTime2DateTime(localDateTime);
  }

  // 计算时间差
  public static Integer getDaysInterval(Date start, Date end) {
    return Math.toIntExact(ChronoUnit.DAYS.between(start.toInstant(), end.toInstant()));
  }

  public static Integer getHoursInterval(Date start, Date end) {
    return Math.toIntExact(ChronoUnit.HOURS.between(start.toInstant(), end.toInstant()));
  }

  public static Integer getMinutesInterval(Date start, Date end) {
    return Math.toIntExact(ChronoUnit.MINUTES.between(start.toInstant(), end.toInstant()));
  }

  public static Integer getSecondsInterval(Date start, Date end) {
    return Math.toIntExact(ChronoUnit.SECONDS.between(start.toInstant(), end.toInstant()));
  }

  public static Integer getSecondsIntervalEndToday() {
    return getIntervalEndToday(ChronoUnit.SECONDS);
  }
  // 计算过完今天还剩多少分钟
  public static Integer getMinutesIntervalEndToday() {
    return getIntervalEndToday(ChronoUnit.MINUTES);
  }

  public static Integer getHoursIntervalEndToday() {
    return getIntervalEndToday(ChronoUnit.HOURS);
  }

  public static Integer getIntervalEndToday(ChronoUnit chronoUnit) {
    if (chronoUnit == null) {
      throw new NullPointerException();
    }
    Date now = new Date();
    LocalDate localDate = DateTransformUtils.date2LocalDate(now);
    LocalDateTime localDateTime = DateTransformUtils.getEndOfDay(localDate);
    Date endOfDay = DateTransformUtils.localDateTime2DateTime(localDateTime);
    return Math.toIntExact(chronoUnit.between(now.toInstant(), endOfDay.toInstant()));
  }
}
