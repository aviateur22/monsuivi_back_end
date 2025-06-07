package com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.stackedBar;

import java.util.List;

public record StackedBarDataSet<T>(
        String type,
        String label,
        String backgroundColor,
        String touchBackgroundColor,
        List<T> values
) {

}
