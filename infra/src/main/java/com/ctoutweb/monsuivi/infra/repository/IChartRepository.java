package com.ctoutweb.monsuivi.infra.repository;

import java.util.List;

/**
 * Custom repository pour récupération des données pour affichage graphique
 */

public interface IChartRepository {
  /**
   * Récupération des quantités de produiuts acheté et vendu pour:
   *  - 1 mois donnée
   *  - 1 vendeur
   * @param sellerId
   * @param date
   * @return List<SoldBuyQuantityProductByMonth>
   */
  List<Object[]> getSoldAndBuyProductQuantityiesByCategoryAndMonthList(Long sellerId, String date);

  /**
   * Récupération du cumul des prix de produiuts acheté et vendu pour:
   * - 1 mois donnée
   * - 1 vendeur
   * @param sellerId - long
   * @param date - String au format 2024-01
   * @return List<Object[]>
   */
  List<Object[]> getSoldAndBuyProductsPricesByCategoryAndMonthList(Long sellerId, String date);

  /**
   * Récupération du cumul des prix de produits acheté et vendu pour:
   *  - 1 année
   *  - 1 vendeur
   * @param sellerId - long
   * @param year - String année
   * @return List<Object[]>
   */
  List<Object[]> getSoldAndBuyProductPricesByCategoryAndYearList(Long sellerId, short year);

  /**
   * Récupération du cumul des quantités  par catégory de produits acheté et vendu pour:
   *  - 1 année
   *  - 1 vendeur
   * @param sellerId - long
   * @param year - short année
   * @return List<Object[]>
   */
  List<Object[]> getSoldAndBuyProductQuantitiesByCategoryAndYearList(Long sellerId, short year);

  /**
   * Récupération du cumul des quantités  toute catégories confondu de produits acheté et vendu pour:
   * - 1 année
   * - 1 vendeur
   * @param sellerId - long
   * @param year - short
   * @return List<Object[]>
   */
  List<Object[]> getSoldAndBuyProductQuantityByYearList(Long sellerId, short year);

  /**
   * Récupération du cumul des prix toute catégories confondue pour les produits acheté et vendu pour:
   * - 1 année
   * - 1 vendeur
   * @param sellerId - long
   * @param year - short
   * @return List<Object[]>
   */
  List<Object[]> getSoldAndBuyProductPriceByYearList(Long sellerId, short year);

}
