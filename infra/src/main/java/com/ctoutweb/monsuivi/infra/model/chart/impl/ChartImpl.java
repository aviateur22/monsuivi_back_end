package com.ctoutweb.monsuivi.infra.model.chart.impl;

import com.ctoutweb.monsuivi.infra.model.chart.IChart;


/**
 * Implementation des données graphique pour un type Doughnut
 */
public record ChartImpl<T>(
        T data
) implements IChart<T> {
  @Override
  public T getData() {
    return data;
  }
}

