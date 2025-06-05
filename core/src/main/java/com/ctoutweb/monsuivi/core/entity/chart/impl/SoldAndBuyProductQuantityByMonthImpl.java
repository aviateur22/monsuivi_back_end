package com.ctoutweb.monsuivi.core.entity.chart.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByMonth;

public record SoldAndBuyProductQuantityByMonthImpl(
        String monthWithYear,
        String quantityType,
        Integer totalQuantity
) implements ISoldAndBuyProductQuantityByMonth {
  @Override
  public String getMonthWithYear() {
    return monthWithYear;
  }

  @Override
  public String getQuantityType() {
    return quantityType;
  }

  @Override
  public Integer getTotalQuantity() {
    return totalQuantity;
  }
}
