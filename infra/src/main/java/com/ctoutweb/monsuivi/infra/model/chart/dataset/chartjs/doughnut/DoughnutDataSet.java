package com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.doughnut;

import java.util.List;

/**
 * Implementation des données graphique pour un type Doughnut
 * @param backgroundColors - Couleurs arrière plan des données
 * @param touchBackgroundColors - Couleurs arriere plan avec souris
 * @param values - Liste des données
 * @param <T> - Format des données (int, double, long...)
 */
public record DoughnutDataSet<T> (
        List<String> backgroundColors,
        List<String> touchBackgroundColors,
        List<T> values
) {}
