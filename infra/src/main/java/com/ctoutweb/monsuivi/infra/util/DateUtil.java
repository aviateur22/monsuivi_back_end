package com.ctoutweb.monsuivi.infra.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {
  /**
   * Convertion d'une LocalDateTime to ZonedDateTime with specific ZoneId
   * @param zoneId
   * @param localDateTime
   * @return ZonedDateTime
   */
  public static ZonedDateTime localDateTimeToZonedDateTime(ZoneId zoneId, LocalDateTime localDateTime) {
    return localDateTime.atZone(zoneId);
  }

  /**
   * Format la date en dd/MM/yyyy
   * @param localDate  LocalDate
   * @return String
   */
  public static String formatToDdMmYy(LocalDate localDate) {
    if(localDate == null)
      return null;

    return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE));
  }

  /**
   * Convertie une String dd/MM/yyyy en LocalDate
   * @param localDate String - Text avec la date
   * @return LocalDate
   */
  public static LocalDate convertStringDdMmYyToLocalDate(String localDate) {
    if(localDate == null)
      return null;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return LocalDate.parse(localDate, formatter);
  }
}
