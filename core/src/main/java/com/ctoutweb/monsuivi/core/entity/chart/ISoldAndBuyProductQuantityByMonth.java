package com.ctoutweb.monsuivi.core.entity.chart;

public interface ISoldAndBuyProductQuantityByMonth {

  /**
   * Renvoie le mois et l'année ex: 01-2022
   * @return String
   */
  String getMonthWithYear();

  /**
   * Label pour identifié si il s'agit d'une quantité de vente ou d'achat
   * @return String
   */
  String getQuantityType();

  /**
   * Quantité total
   * @return Integer
   */
  Integer getTotalQuantity();
}
