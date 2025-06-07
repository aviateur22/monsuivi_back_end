package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndMonth;

import java.util.List;

public interface ISoldAndBuyProductPriceByCategoryAndMonthGateway {
  /**
   * Vérification si le vendeur peut récupérer les données du graphique
   * @param sellerId long
   * @return boolean
   */
  boolean canSellerDisplayChartInformation(long sellerId);

  /**
   * Récupération d'une liste de données sur la vente et achat de produit catégorisé par catégorie et mois
   * @param sellerId long
   * @return List<ISoldAndBuyProductPriceByCategoryAndMonth>
   */
  List<ISoldAndBuyProductPriceByCategoryAndMonth> getSoldAndBuyProductPriceByCategoryAndMonthList(long sellerId, String requestedMonth);
}
