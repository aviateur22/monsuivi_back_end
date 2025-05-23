package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndYear;

import java.util.List;

public interface ISoldAndBuyProductPriceBuyByCategoryAndYearOutput  {
  /**
   * List des données à afficher
   * @return List<ISoldAndBuyProductQuantityByCategoryAndMonth>
   */
  List<ISoldAndBuyProductPriceByCategoryAndYear> getDatas();

  /**
   * Année demandée pour les données affichées
   * @return short
   */
  short getRequestedYear();
}
