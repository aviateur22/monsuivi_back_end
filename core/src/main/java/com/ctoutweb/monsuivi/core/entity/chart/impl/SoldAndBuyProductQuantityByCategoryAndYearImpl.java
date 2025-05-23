package com.ctoutweb.monsuivi.core.entity.chart.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndYear;

public record SoldAndBuyProductQuantityByCategoryAndYearImpl(
        String productCategoryName,
        String productBackgroundColor,
        String productTouchBackgroundColor,
        String year,
        int buyQuantityProduct,
        int soldQuantityProduct

) implements ISoldAndBuyProductQuantityByCategoryAndYear {
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
  public Integer getBuyQuantityProduct() {
    return buyQuantityProduct;
  }

  @Override
  public Integer getSoldQuantityProduct() {
    return soldQuantityProduct;
  }
}
