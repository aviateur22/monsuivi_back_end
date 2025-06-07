package com.ctoutweb.monsuivi.core.entity.chart.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndMonth;

public record SoldAndBuyProductPriceByCategoryAndMonthImpl(
        String productCategoryName,
        String productBackgroundColor,
        String productTouchBackgroundColor,
        double priceBuy,
        double priceSold) implements ISoldAndBuyProductPriceByCategoryAndMonth {

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
  public double getBuyPriceProduct() {
    return priceBuy;
  }

  @Override
  public double getSoldPriceProduct() {
    return priceSold;
  }
}
