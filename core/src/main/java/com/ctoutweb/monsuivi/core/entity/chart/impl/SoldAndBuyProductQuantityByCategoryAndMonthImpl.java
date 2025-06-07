package com.ctoutweb.monsuivi.core.entity.chart.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndMonth;

public record SoldAndBuyProductQuantityByCategoryAndMonthImpl(
        String productCategoryName,
        String productBackgroundColor,
        String productTouchBackgroundColor,
        Integer quantiyBuy,
        Integer quantitySold
) implements ISoldAndBuyProductQuantityByCategoryAndMonth {
  @Override
  public String getProductCategoryName() {
    return productCategoryName;
  }

  @Override
  public String getProductBackgroundColor() {
    return productBackgroundColor;
  }

  @Override
  public String getProductBackgroundTouchColor() {
    return productTouchBackgroundColor;
  }

  @Override
  public String getMonthWithYear() {
    return null;
  }

  @Override
  public Integer getBuyQuantityProduct() {
    return quantiyBuy;
  }

  @Override
  public Integer getSoldQuantityProduct() {
    return quantitySold;
  }
}
