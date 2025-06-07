package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndYear;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.ISoldAndBuyProductQuantityByCategoryAndYearOutput;

import java.util.List;

public record SoldAndBuyProductQuantityByCategoryAndYearOutputImpl(
        List<ISoldAndBuyProductQuantityByCategoryAndYear> datas,
        short requestedYear
) implements ISoldAndBuyProductQuantityByCategoryAndYearOutput {
  @Override
  public List<ISoldAndBuyProductQuantityByCategoryAndYear> getDatas() {
    return datas;
  }

  @Override
  public short getRequestedYear() {
    return requestedYear;
  }
}
