package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndMonth;
import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndMonth;

import java.util.List;

public interface ISoldAndBuyProductPriceByCategoryAndMonthOutput {
  /**
   * List des données à afficher
   * @return List<ISoldAndBuyProductQuantityByCategoryAndMonth>
   */
  List<ISoldAndBuyProductPriceByCategoryAndMonth> getDatas();

  /**
   * Année demandée pour les données affichées
   * @return short
   */
  short getRequestedYear();

  /**
   * Mois en lettre pour les données affichée
   *
   * @return String
   */
  String getRequestedMonth();
}
