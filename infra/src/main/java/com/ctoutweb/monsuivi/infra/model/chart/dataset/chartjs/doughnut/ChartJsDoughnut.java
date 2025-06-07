package com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.doughnut;

import java.util.List;

/**
 * Données a afficher pour un graphique de type chartJs *
 * @param <T> - Type de donnée sur le graphique( Integer, Double,..)
 */
public record ChartJsDoughnut<T>(
    /**
     * Liste contenant les labels à afficher sur l'axe x
     * @return List<String>
     */
      List<String> axisLabels,

      /**
       * Liste de données
       */
    DoughnutDataSet<T> dataSet
) {
}
