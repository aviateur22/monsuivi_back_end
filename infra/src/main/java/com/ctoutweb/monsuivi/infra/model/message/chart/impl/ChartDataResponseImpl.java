package com.ctoutweb.monsuivi.infra.model.message.chart.impl;

import com.ctoutweb.monsuivi.infra.model.chart.IChart;
import com.ctoutweb.monsuivi.infra.model.message.chart.IChartDataResponse;

public record ChartDataResponseImpl<T>(
        IChart data,
        String responseMessage
) implements IChartDataResponse {
  @Override
  public IChart<T> getData() {
    return data;
  }

  @Override
  public String getResponseMessage() {
    return responseMessage;
  }
}
