package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.impl;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByMonth;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.ISoldAndBuyProductQuantityByMonthOutput;

import java.util.List;

public record SoldAndBuyProductQuantityByMonthOutpuImpl(
        List<ISoldAndBuyProductQuantityByMonth> datas,
        String requestedMonth,
        short requestedYear

)
implements ISoldAndBuyProductQuantityByMonthOutput<ISoldAndBuyProductQuantityByMonth> {
  @Override
  public List getDatas() {
    return datas;
  }

  @Override
  public short getRequestedYear() {
    return requestedYear;
  }

  @Override
  public String getRequestedMonth() {
    return requestedMonth;
  }
}
