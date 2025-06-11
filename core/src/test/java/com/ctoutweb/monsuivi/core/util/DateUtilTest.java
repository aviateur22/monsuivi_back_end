package com.ctoutweb.monsuivi.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DateUtilTest {

  @Test
  public void getLocalDateFromAPeriodInDay() {
    /**
     * given
     */
    short periodInDay = 7;

    /**
     * when
     */
    var actualLocalDate = DateUtil.getLocalDateFromAPeriodInDay(periodInDay);

    /**
     * then
     */
    Assertions.assertEquals(LocalDate.now().minusDays(periodInDay), actualLocalDate);
  }
}
