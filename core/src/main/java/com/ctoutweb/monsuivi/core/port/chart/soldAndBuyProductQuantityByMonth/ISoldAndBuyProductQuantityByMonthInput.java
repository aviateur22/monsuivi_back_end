package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth;

import com.ctoutweb.monsuivi.core.port.chart.common.IBaseInput;

public interface ISoldAndBuyProductQuantityByMonthInput extends IBaseInput {
  /**
   * mois
   * @return short
   */
  short getMonth();
}
