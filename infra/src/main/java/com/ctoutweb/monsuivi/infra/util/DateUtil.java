package com.ctoutweb.monsuivi.infra.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
}
