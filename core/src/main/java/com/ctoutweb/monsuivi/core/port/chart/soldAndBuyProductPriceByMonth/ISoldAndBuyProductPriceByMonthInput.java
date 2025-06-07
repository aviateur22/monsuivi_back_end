package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth;

import com.ctoutweb.monsuivi.core.port.chart.common.IBaseInput;

public interface ISoldAndBuyProductPriceByMonthInput extends IBaseInput {
  /**
   * mois
   * @return short
   */
  short getMonth();
}
