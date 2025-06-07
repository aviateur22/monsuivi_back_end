package com.ctoutweb.monsuivi.core.entity.chart;

public interface ISoldAndBuyProductPriceByCategoryAndYear {
  String getProductCategoryName();
  String getProductBackgroundColor();
  String getProductBackgroundTouchColor();
  String getYear();
  double getBuyPriceProduct();
  double getSoldPriceProduct();
}
