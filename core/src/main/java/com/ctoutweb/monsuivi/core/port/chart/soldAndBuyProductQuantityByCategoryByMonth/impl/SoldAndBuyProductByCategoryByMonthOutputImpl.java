package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndMonth;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.ISoldAndBuyProductByCategoryByMonthOutput;

import java.util.List;

public record SoldAndBuyProductByCategoryByMonthOutputImpl(
        List<ISoldAndBuyProductQuantityByCategoryAndMonth> datas,
        short year,
        String month)
        implements ISoldAndBuyProductByCategoryByMonthOutput {
  @Override
  public short getRequestedYear() {
    return year;
  }

  @Override
  public String getRequestedMonth() {
    return month;
  }

  @Override
  public List<ISoldAndBuyProductQuantityByCategoryAndMonth> getDatas() {
    return datas;
  }
}
