package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth;

import com.ctoutweb.monsuivi.core.port.chart.common.IBaseOutput;

public interface ISoldAndBuyProductQuantityByMonthOutput<T> extends IBaseOutput {
  /**
   * Mois en lettre pour les données affichée
   * @return String
   */
  String getRequestedMonth();
}
