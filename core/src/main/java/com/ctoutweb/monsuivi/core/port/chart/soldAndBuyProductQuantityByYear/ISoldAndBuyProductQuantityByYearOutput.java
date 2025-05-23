package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByYear;

import java.util.List;

public interface ISoldAndBuyProductQuantityByYearOutput {
  /**
   * List des données à afficher
   * @return List<ISoldAndBuyProductPriceByYear>
   */
  List<ISoldAndBuyProductQuantityByYear> getDatas();

  /**
   * Année demandée pour les données affichées
   * @return short
   */
  short getRequestedYear();
}
