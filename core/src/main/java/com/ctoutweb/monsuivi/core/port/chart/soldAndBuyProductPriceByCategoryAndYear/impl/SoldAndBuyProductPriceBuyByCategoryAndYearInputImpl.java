package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.impl;

import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.ISoldAndBuyProductPriceBuyByCategoryAndYearInput;

public record SoldAndBuyProductPriceBuyByCategoryAndYearInputImpl(
        long sellerId,
        short year
) implements ISoldAndBuyProductPriceBuyByCategoryAndYearInput {
  @Override
  public long getSellerId() {
    return sellerId;
  }

  @Override
  public short getYearRequest() {
    return year;
  }
}
