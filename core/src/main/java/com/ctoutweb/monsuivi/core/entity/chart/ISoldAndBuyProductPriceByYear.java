package com.ctoutweb.monsuivi.core.entity.chart;

public interface ISoldAndBuyProductPriceByYear {
  String getRequestedYear();
  String getPriceType();
  double getTotalPrice();
}
