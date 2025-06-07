package com.ctoutweb.monsuivi.core.port.chart.common;

import java.util.List;
public interface IBaseMonthGateway<T> {
  /**
   * Vérification si le vendeur peut récupérer les données du graphique
   * @param sellerId long
   * @return boolean
   */
  boolean canSellerDisplayChartInformation(long sellerId);

  /**
   * Récupération d'une liste de données basé sur un mois ex: 2023-01
   * @param sellerId long
   * @param requestedMonth String - Mois de la recherche ex: 2023-01
   * @return List<ISoldAndBuyProductPriceByYear>
   */
  List<T> getProductDataByMonth(long sellerId, String requestedMonth);
}
