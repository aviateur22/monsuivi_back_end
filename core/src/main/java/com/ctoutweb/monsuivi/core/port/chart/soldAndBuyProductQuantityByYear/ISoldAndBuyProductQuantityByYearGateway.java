package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByYear;

import java.util.List;

public interface ISoldAndBuyProductQuantityByYearGateway {
  /**
   * Vérification si le vendeur peut récupérer les données du graphique
   * @param sellerId long
   * @return boolean
   */
  boolean canSellerDisplayChartInformation(long sellerId);

  /**
   * Récupération d'une liste de données sur la vente et achat de produit catégorisé par année
   * @param sellerId long
   * @return List<ISoldAndBuyProductQuantityByYear>
   */
  List<ISoldAndBuyProductQuantityByYear> getSoldAndBuyProductQuantityByYearList(long sellerId, short requestedYear);
}
