package com.ctoutweb.monsuivi.core.entity.chart;

public interface ISoldAndBuyProductPriceByMonth {
  /**
   * Renvoie le mois et l'année ex: 01-2022
   * @return String
   */
  String getMonthWithYear();

  /**
   * Label pour identifié si le prix est de vente ou d'achat
   * @return String
   */
  String getPriceType();

  /**
   * Prix total
   * @return Integer
   */
  Double getTotalPrice();
}
