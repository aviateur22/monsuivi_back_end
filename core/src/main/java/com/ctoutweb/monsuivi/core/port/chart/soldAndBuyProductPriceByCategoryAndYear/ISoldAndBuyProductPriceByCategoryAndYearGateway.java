package com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndYear;

import java.util.List;

public interface ISoldAndBuyProductPriceByCategoryAndYearGateway {
  /**
   * Vérification si le vendeur peut récupérer les données du graphique
   * @param sellerId long
   * @return boolean
   */
  boolean canSellerDisplayChartInformation(long sellerId);

  /**
   * Récupération d'une liste de données sur la vente et achat de produit catégorisé par catégorie et année
   * @param sellerId long
   * @return List<ISoldAndBuyProductPriceByCategoryAndYear>
   */
  List<ISoldAndBuyProductPriceByCategoryAndYear> getSoldAndBuyProductPriceByCategoryAndYearList(long sellerId, short year);
}
