package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByYear;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.ISoldAndBuyProductQuantityByYearOutput;

import java.util.List;

public record SoldAndBuyProductQuantityByYearOutputImpl(
        List<ISoldAndBuyProductQuantityByYear> datas,
        short requestYear
) implements ISoldAndBuyProductQuantityByYearOutput {
  @Override
  public List<ISoldAndBuyProductQuantityByYear> getDatas() {
    return datas;
  }

  @Override
  public short getRequestedYear() {
    return requestYear;
  }
}
