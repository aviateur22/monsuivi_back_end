package com.ctoutweb.monsuivi.core.entity.chart.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByYear;

public record SoldAndBuyProductPriceByYearImpl(
        String priceType,
        double totalPrice,
        String requestedYear
) implements ISoldAndBuyProductPriceByYear {

  @Override
  public String getRequestedYear() {
    return requestedYear;
  }

  @Override
  public String getPriceType() {
    return priceType;
  }

  @Override
  public double getTotalPrice() {
    return totalPrice;
  }
}
