package com.ctoutweb.monsuivi.infra.dto.response.chart;

import com.ctoutweb.monsuivi.infra.model.message.chart.IChartDataResponse;

public record SoldAndBuyProductPriceByCategoryAndYearResponseDto<T>(
        IChartDataResponse<T> doughnutChartDataProductSold,
        IChartDataResponse<T> doughnutChartDataProductBuy
) {}
