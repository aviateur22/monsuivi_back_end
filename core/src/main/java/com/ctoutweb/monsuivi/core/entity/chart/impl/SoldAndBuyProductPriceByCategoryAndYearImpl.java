package com.ctoutweb.monsuivi.core.entity.chart.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndYear;

public record SoldAndBuyProductPriceByCategoryAndYearImpl(
        String productCategoryName,
        String productBackgroundColor,
        String productTouchBackgroundColor,
        String year,
        double priceBuy,
        double priceSold
) implements ISoldAndBuyProductPriceByCategoryAndYear {
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
  public String getYear() {
    return year;
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
