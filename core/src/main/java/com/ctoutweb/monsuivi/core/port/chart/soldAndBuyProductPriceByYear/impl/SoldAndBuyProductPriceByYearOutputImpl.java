package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByYear;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.ISoldAndBuyProductPriceByYearOutput;

import java.util.List;

public record SoldAndBuyProductPriceByYearOutputImpl(
        List<ISoldAndBuyProductPriceByYear> datas,
        short requestYear
) implements ISoldAndBuyProductPriceByYearOutput {
  @Override
  public List<ISoldAndBuyProductPriceByYear> getDatas() {
    return datas;
  }

  @Override
  public short getRequestedYear() {
    return requestYear;
  }
}
