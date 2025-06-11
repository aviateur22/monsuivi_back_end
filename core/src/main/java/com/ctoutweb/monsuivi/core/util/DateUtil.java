package com.ctoutweb.monsuivi.core.util;

import java.time.LocalDate;

public class DateUtil {
  private DateUtil() {
    throw new IllegalArgumentException("DateUtil - Class Static");
  }

  /**
   * Renvoie une LocalDate déterminé a partir du jour du calcul a laquelle on a soustrait un nombre de jour
    * @param periodInDay - short
   * @return LocalDate
   */
  public static LocalDate getLocalDateFromAPeriodInDay(short periodInDay) {
    return LocalDate.now().minusDays(periodInDay);
  }
}
