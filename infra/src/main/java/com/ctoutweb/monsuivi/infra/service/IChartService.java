package com.ctoutweb.monsuivi.infra.service;

import com.ctoutweb.monsuivi.infra.dto.response.chart.*;

public interface IChartService {
  /**
   * Récupération des données quantité de produits ventitlé par mois et catégory de produit
   * Ventes et achats
   * @param sellerId - long vendeur
   * @param month - short
   * @param year- short
   * @return SoldAndBuyQuantityProductByCategoryAndMonthResponseDto
   */
  SoldAndBuyQuantityProductByCategoryAndMonthResponseDto getSoldAndBuyProductQuantityByCategoryAndMonth(
          long sellerId,
          short month,
          short year);


  /**
   * Récupération des prix produits ventitlé par mois et catégory de produit
   * @param sellerId - long
   * @param month - short
   * @param year - short
   * @return SoldAndBuyProductPriceByCategoryAndMonthDto
   */
  SoldAndBuyProductPriceByCategoryAndMonthResponseDto getSoldAndBuyProductPriceByCategoryAndMonth(
          long sellerId,
          short month,
          short year
  );

  /**
   * Récupération des prix produits ventilé sur 1 année et par catgegorie produit
   * @param sellerId - long
   * @param year - short
   * @return SoldAndBuyProductPriceByCategoryAndYearResponseDto
   */
  SoldAndBuyProductPriceByCategoryAndYearResponseDto getSoldAndBuyProductPriceByCategoryAndYear(
          long sellerId,
          short year
  );

  /**
   * Récupération des quantité produits ventilé sur 1 année et par catgegorie produit
   * @param sellerId - long
   * @param year - short
   * @return SoldAndBuyProductQuantityByCategoryAndYearResponseDto
   */
  SoldAndBuyProductQuantityByCategoryAndYearResponseDto getSoldAndBuyProductQuantityByCategoryAndYear(
          long sellerId,
          short year
  );

  /**
   * Récupération des quantité produits ventilé sur 1 année et toute catégorie confondu
   * @param sellerId - long
   * @param year - short
   * @return SoldAndBuyQuantityProductByYearResponseDto
   */
  SoldAndBuyQuantityProductByYearResponseDto getSoldAndBuyQuantityProductByYear(
          long sellerId,
          short year
  );

  /**
   * Récupération des prix produits ventilé sur 1 année et toute catégorie confondu
   * @param sellerId - long
   * @param year - short
   * @return SoldAndBuyProductPriceByYearResponseDto
   */
  SoldAndBuyProductPriceByYearResponseDto getSoldAndBuyProductPriceByYear(
          long sellerId,
          short year
  );

  /**
   * Récupération du prix total de vente et achat des produit pour 1 mois données
   * @param sellerId - long
   * @param month - short - mois demandé ex 01 pour janvier
   * @param year - short - année demandé ex 2025
   * @return SoldAndBuyProductPriceByMonthDto
   */
  SoldAndBuyProductPriceByMonthDto getSoldAndBuyProductPriceByMonth(
          long sellerId,
          short month,
          short year
  );

  /**
   * Récupération des quantité total de vente et achat des produits pour 1 mois
   * @param sellerId - integer - Id du vendeur
   * @param month - short - mois demandé ex 01 pour janvier
   * @param year - short - année demandé ex 2025
   * @return SoldAndBuyProductQuantityByMonthDto
   */
  SoldAndBuyProductQuantityByMonthDto getSoldAndBuyProductQuantityByMonth(
          long sellerId,
          short month,
          short year
  );


}
