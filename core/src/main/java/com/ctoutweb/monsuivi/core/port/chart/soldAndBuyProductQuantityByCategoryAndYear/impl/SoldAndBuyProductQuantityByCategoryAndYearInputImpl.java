package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.impl;

import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.ISoldAndBuyProductQuantityByCategoryAndYearInput;

public record SoldAndBuyProductQuantityByCategoryAndYearInputImpl(
        long sellerId,
        short yearRequest
) implements ISoldAndBuyProductQuantityByCategoryAndYearInput {
  @Override
  public long getSellerId() {
    return sellerId;
  }

  @Override
  public short getYearRequest() {
    return yearRequest;
  }
}
