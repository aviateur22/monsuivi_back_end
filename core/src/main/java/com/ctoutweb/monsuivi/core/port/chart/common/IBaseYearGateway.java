package com.ctoutweb.monsuivi.core.port.chart.common;

import java.util.List;

public interface IBaseYearGateway<T> {
  /**
   * Vérification si le vendeur peut récupérer les données du graphique
   * @param sellerId long
   * @return boolean
   */
  boolean canSellerDisplayChartInformation(long sellerId);

  /**
   * Récupération d'une liste de données basé sur une année
   * @param sellerId long
   * @param year short -  année recherché
   * @return List<ISoldAndBuyProductPriceByYear>
   */
  List<T> getProductDataByYear(long sellerId, short year);

}
