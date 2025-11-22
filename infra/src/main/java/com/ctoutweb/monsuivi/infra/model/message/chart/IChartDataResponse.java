package com.ctoutweb.monsuivi.infra.model.message.chart;

import com.ctoutweb.monsuivi.infra.model.chart.IChart;
import com.ctoutweb.monsuivi.infra.dto.response.response.IResponseMessage;

/**
 * Renvoie les données du composant graphique et un message
 * @param <T> - Tyoe de données
 */
public interface IChartDataResponse<T> extends IResponseMessage {
  public IChart<T> getData();
}
