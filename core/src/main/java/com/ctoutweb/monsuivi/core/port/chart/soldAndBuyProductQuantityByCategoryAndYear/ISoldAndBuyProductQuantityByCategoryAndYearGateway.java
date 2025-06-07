package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndYear;

import java.util.List;

public interface ISoldAndBuyProductQuantityByCategoryAndYearGateway {
  /**
   * Vérification si le vendeur peut récupérer les données du graphique
   * @param sellerId long
   * @return boolean
   */
  boolean canSellerDisplayChartInformation(long sellerId);

  /**
   * Récupération d'une liste de données sur la vente et achat de produit catégorisé année
   * @param sellerId long
   * @return List<ISoldAndBuyProductQuantityByCategoryAndYear>
   */
  List<ISoldAndBuyProductQuantityByCategoryAndYear> getSoldAndBuyProductQuantityByCategoryAndYearList(long sellerId, short year);
}
