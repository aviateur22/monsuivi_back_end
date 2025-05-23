package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.impl;

import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.ISoldAndBuyProductByCategoryByMonthInput;

public record SoldAndBuyProductByCategoryByMonthInputImpl(
        long sellerId,
        short year,
        short month
)
implements ISoldAndBuyProductByCategoryByMonthInput {
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
