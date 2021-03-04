package com.wenky.starter.custom.util.date;

import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @program: spring-boot-starter-custom-mail-mongodb
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-23 15:32
 */
public class FormatDateUtils {

  private static final String RFC3339_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZ";
  private static final String YYYY_MM_DD_DATE_FORMAT = "yyyy-MM-dd";

  public static Date RFC3339StringToDate(String s) {
    try {
      return StringUtils.isBlank(s) ? null : DateUtils.parseDate(s, RFC3339_DATE_FORMAT);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String RFC3339Time(Date date) {
    return date == null ? null : DateFormatUtils.format(date, RFC3339_DATE_FORMAT);
  }
}
