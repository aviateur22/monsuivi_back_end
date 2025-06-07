package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth;

public interface ISoldAndBuyProductPriceByCategoryAndMonthInput {
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
