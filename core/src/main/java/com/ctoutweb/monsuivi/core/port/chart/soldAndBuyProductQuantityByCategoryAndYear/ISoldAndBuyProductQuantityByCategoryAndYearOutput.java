package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndYear;
import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndYear;

import java.util.List;

public interface ISoldAndBuyProductQuantityByCategoryAndYearOutput {
  /**
   * List des données à afficher
   * @return List<ISoldAndBuyProductQuantityByCategoryAndYear>
   */
  List<ISoldAndBuyProductQuantityByCategoryAndYear> getDatas();

  /**
   * Année demandée pour les données affichées
   * @return short
   */
  short getRequestedYear();
}
