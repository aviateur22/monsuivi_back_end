package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByMonth;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.ISoldAndBuyProductPriceByMonthOutput;

import java.util.List;

public record SoldAndBuyProductPriceByMonthOutputImpl(
        List<ISoldAndBuyProductPriceByMonth> datas,
        String requestedMonth,
        short requestedYear
)
implements ISoldAndBuyProductPriceByMonthOutput<ISoldAndBuyProductPriceByMonth> {
  @Override
  public List getDatas() {
    return datas;
  }

  @Override
  public short getRequestedYear() {
    return requestedYear;
  }

  @Override
  public String getRequestedMonth() {
    return requestedMonth;
  }
}
