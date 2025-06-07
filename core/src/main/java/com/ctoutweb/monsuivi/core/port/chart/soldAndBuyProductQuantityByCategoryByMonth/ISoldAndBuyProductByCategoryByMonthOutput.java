package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndMonth;

import java.util.List;

public interface ISoldAndBuyProductByCategoryByMonthOutput {
  /**
   * List des données à afficher
   * @return List<ISoldAndBuyProductQuantityByCategoryAndMonth>
   */
  List<ISoldAndBuyProductQuantityByCategoryAndMonth> getDatas();

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
