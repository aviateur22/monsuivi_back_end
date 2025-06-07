package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.impl;

import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.ISoldAndBuyProductPriceByMonthInput;

public record SoldAndBuyProductPriceByMonthInputImpl(
        long sellerId,
        short year,
        short month
)
implements ISoldAndBuyProductPriceByMonthInput {
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
