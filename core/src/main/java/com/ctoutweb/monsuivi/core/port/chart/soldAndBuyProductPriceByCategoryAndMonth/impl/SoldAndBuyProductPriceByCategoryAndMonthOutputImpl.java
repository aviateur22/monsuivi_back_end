package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndMonth;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.ISoldAndBuyProductPriceByCategoryAndMonthOutput;

import java.util.List;

public record SoldAndBuyProductPriceByCategoryAndMonthOutputImpl(
        List<ISoldAndBuyProductPriceByCategoryAndMonth> datas,
        short year,
        String month) implements ISoldAndBuyProductPriceByCategoryAndMonthOutput {
  @Override
  public List<ISoldAndBuyProductPriceByCategoryAndMonth> getDatas() {
    return datas;
  }

  @Override
  public short getRequestedYear() {
    return year;
  }

  @Override
  public String getRequestedMonth() {
    return month;
  }
}
