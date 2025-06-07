package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth;

import com.ctoutweb.monsuivi.core.port.chart.common.IBaseOutput;

public interface ISoldAndBuyProductPriceByMonthOutput<T> extends IBaseOutput {
  /**
   * Mois en lettre pour les données affichée
   * @return String
   */
  String getRequestedMonth();
}
