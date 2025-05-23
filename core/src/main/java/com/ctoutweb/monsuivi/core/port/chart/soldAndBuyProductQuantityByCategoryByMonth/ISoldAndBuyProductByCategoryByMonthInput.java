package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth;

public interface ISoldAndBuyProductByCategoryByMonthInput {
  long getSellerId();

  /**
   * Année
   * @return short
   */
  short getYear();

  /**
   * mois
   * @return short
   */
  short getMonth();
}
