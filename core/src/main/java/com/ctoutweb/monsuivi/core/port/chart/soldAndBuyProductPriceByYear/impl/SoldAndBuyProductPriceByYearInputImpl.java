package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.impl;

import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.ISoldAndBuyProductPriceByYearInput;

public record SoldAndBuyProductPriceByYearInputImpl(long sellerId, short yearRequest) implements ISoldAndBuyProductPriceByYearInput {
  @Override
  public long getSellerId() {
    return sellerId;
  }

  @Override
  public short getYearRequest() {
    return yearRequest;
  }
}
