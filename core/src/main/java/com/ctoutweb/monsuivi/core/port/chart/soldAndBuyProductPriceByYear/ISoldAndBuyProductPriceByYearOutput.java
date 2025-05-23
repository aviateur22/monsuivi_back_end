package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByYear;

import java.util.List;

public interface ISoldAndBuyProductPriceByYearOutput {
  /**
   * List des données à afficher
   * @return List<ISoldAndBuyProductPriceByYear>
   */
  List<ISoldAndBuyProductPriceByYear> getDatas();

  /**
   * Année demandée pour les données affichées
   * @return short
   */
  short getRequestedYear();
}
