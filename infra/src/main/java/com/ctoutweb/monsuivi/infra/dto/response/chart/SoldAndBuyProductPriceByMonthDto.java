package com.ctoutweb.monsuivi.infra.dto.response.chart;

import com.ctoutweb.monsuivi.infra.model.message.chart.IChartDataResponse;

public record SoldAndBuyProductPriceByMonthDto<T>(
        IChartDataResponse<T> stackedBarChartDataProductPrice
) {}
