package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndMonth;

import java.util.List;

public interface ISoldAndBuyQuantityProductByCategoryByMonthGateway {
  /**
   * Vérification si le vendeur peut récupérer les données du graphique
   * @param sellerId long
   * @return boolean
   */
  boolean canSellerDisplayChartInformation(long sellerId);

  /**
   * Récupération d'une liste de données sur la vente et achat de produit catégorisé par catégorie et mois
   * @param sellerId long
   * @return List<ISoldAndBuyProductQuantityByCategoryAndMonth>
   */
  List<ISoldAndBuyProductQuantityByCategoryAndMonth> getSoldAndBuyProductQuantityByCategoryAndMonthList(long sellerId, String requestedMonth);
}
