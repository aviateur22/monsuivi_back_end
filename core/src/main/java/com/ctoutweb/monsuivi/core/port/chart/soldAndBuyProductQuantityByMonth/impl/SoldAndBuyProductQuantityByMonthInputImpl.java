package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.impl;

import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.ISoldAndBuyProductQuantityByMonthInput;

public record SoldAndBuyProductQuantityByMonthInputImpl(
        long sellerId,
        short year,
        short month
)
implements ISoldAndBuyProductQuantityByMonthInput {
  @Override
  public long getSellerId() {
    return sellerId;
  }

  @Override
  public short getYear() {
    return year;
  }

  @Override
  public short getMonth() {
    return month;
  }
}
