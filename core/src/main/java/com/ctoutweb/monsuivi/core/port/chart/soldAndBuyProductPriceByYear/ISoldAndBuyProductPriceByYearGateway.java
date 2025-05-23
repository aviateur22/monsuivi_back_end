package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByYear;

import java.util.List;

public interface ISoldAndBuyProductPriceByYearGateway {
  /**
   * Vérification si le vendeur peut récupérer les données du graphique
   * @param sellerId long
   * @return boolean
   */
  boolean canSellerDisplayChartInformation(long sellerId);

  /**
   * Récupération d'une liste de données sur la vente et achat de produit catégorisé année
   * @param sellerId long
   * @return List<ISoldAndBuyProductPriceByYear>
   */
  List<ISoldAndBuyProductPriceByYear> getSoldAndBuyProductPriceByYearList(long sellerId, short year);
}
