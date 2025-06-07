package com.ctoutweb.monsuivi.core.entity.chart.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByMonth;

public record SoldAndBuyProductPriceByMonthImpl(
        String monthWithYear,
        String priceType,
        Double totalPrice
) implements ISoldAndBuyProductPriceByMonth {
  @Override
  public String getMonthWithYear() {
    return monthWithYear;
  }

  @Override
  public String getPriceType() {
    return priceType;
  }

  @Override
  public Double getTotalPrice() {
    return totalPrice;
  }
}
