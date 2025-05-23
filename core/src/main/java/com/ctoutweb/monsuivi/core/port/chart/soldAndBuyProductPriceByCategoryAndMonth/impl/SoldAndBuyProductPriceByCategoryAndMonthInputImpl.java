package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.impl;

import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.ISoldAndBuyProductPriceByCategoryAndMonthInput;

public record SoldAndBuyProductPriceByCategoryAndMonthInputImpl(
        long sellerId,
        short year,
        short month
) implements ISoldAndBuyProductPriceByCategoryAndMonthInput {
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
