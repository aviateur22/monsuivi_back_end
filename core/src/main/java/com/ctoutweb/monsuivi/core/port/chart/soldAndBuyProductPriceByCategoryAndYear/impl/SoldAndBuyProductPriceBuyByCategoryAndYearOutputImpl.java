package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndYear;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.ISoldAndBuyProductPriceBuyByCategoryAndYearOutput;

import java.util.List;

public record SoldAndBuyProductPriceBuyByCategoryAndYearOutputImpl(
        List<ISoldAndBuyProductPriceByCategoryAndYear> datas,
        short requestYear
) implements ISoldAndBuyProductPriceBuyByCategoryAndYearOutput {
  @Override
  public List<ISoldAndBuyProductPriceByCategoryAndYear> getDatas() {
    return datas;
  }

  @Override
  public short getRequestedYear() {
    return requestYear;
  }
}
