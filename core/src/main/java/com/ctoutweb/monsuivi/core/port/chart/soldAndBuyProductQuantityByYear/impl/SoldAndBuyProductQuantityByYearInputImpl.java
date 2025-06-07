package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.impl;

import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.ISoldAndBuyProductQuantityByYearInput;

public record SoldAndBuyProductQuantityByYearInputImpl(
        long sellerId,
        short yearRequest
) implements ISoldAndBuyProductQuantityByYearInput {
  @Override
  public long getSellerId() {
    return sellerId;
  }

  @Override
  public short getYearRequest() {
    return yearRequest;
  }
}
