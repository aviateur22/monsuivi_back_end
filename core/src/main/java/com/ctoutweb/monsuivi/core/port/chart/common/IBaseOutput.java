package com.ctoutweb.monsuivi.core.port.chart.common;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndMonth;

import java.util.List;

public interface IBaseOutput<T> {
  /**
   * List des données récupérées
   * @return List<T>
   */
  List<T> getDatas();

  /**
   * Année demandée pour les données affichées
   * @return short
   */
  short getRequestedYear();
}
