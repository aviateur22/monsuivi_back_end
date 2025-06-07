package com.ctoutweb.monsuivi.core.entity.chart.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByYear;

public record SoldAndBuyProductQuantityByYearImpl(
        String quantityType,
        int totalQuantity,
        String year
) implements ISoldAndBuyProductQuantityByYear {
  @Override
  public String getYear() {
    return year;
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
