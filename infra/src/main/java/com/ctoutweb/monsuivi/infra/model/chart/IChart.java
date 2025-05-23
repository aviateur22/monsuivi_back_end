package com.ctoutweb.monsuivi.infra.model.chart;

/**
 * Abstraction permettant de contenir les données d'un graphique
 * @param <T> - Model de données contenant les données graphique (
 *           ex: Un graphique de type doughnut ou stoackedBar...)
 */
public interface IChart<T> {

  /**
   * Données a afficher
   * @return List<T>
   */
  T getData();
}
