package com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.stackedBar;

import java.util.List;

/**
 * Implementation des données graphique pour un type StackedBar
 * @param axisLabels - Labels a afficher sur l'axe
 * @param datasets - List des dataset composant le graphique
 */
public record ChartJsStackedBar(
        List<String> axisLabels,
        List<StackedBarDataSet> datasets

) {}
