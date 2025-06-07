package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductPriceByMonth;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByMonth;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.ISoldAndBuyProductPriceByMonthInput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.dto.response.chart.SoldAndBuyProductPriceByMonthDto;
import com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.stackedBar.ChartJsStackedBar;
import com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.stackedBar.StackedBarDataSet;
import com.ctoutweb.monsuivi.infra.model.message.chart.IChartDataResponse;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ctoutweb.monsuivi.infra.constant.Constant.*;
import static com.ctoutweb.monsuivi.infra.constant.Constant.HOVER_BUY_COLOR;

@Component
public class SoldAndBuyProductPriceByMonthMapper {

  private final CoreFactory coreFactory;
  private final InfraFactory infraFactory;

  public SoldAndBuyProductPriceByMonthMapper(CoreFactory coreFactory, InfraFactory infraFactory) {
    this.coreFactory = coreFactory;
    this.infraFactory = infraFactory;
  }
  public ISoldAndBuyProductPriceByMonthInput getInput(long sellerId,short monthrequest, short yearRequest){
    return coreFactory.getSoldAndBuyProductPriceByMonthInputImpl(sellerId, yearRequest, monthrequest);
  }

  public SoldAndBuyProductPriceByMonthDto mapToDto(
          List<ISoldAndBuyProductPriceByMonth> datas,
          String monthRequest,
          short yearRequest) {
    StackedBarDataSet<Double> buyProductPriceDataSet = mapToStackedBarProductBuyDataSet(datas);
    StackedBarDataSet<Double> soldProductPriceDataSet = mapToStackedBarProductSoldDataSet(datas);

    String axisLabelString = String.format("%s-%s", monthRequest, yearRequest);
    ChartJsStackedBar SoldAndBuyProductPriceByYearStackedBar =
            infraFactory.getStackedBar(
                    List.of(String.valueOf(axisLabelString)),
                    List.of(buyProductPriceDataSet, soldProductPriceDataSet)
            );

    IChartDataResponse chartDataResponse = infraFactory.getChartDataResponseImpl(
            SoldAndBuyProductPriceByYearStackedBar,
            String.format("Totale des dépenses et des ventes pour %s %s", monthRequest, yearRequest));

    return new SoldAndBuyProductPriceByMonthDto<>(chartDataResponse);
  }

  private StackedBarDataSet<Double> mapToStackedBarProductSoldDataSet(List<ISoldAndBuyProductPriceByMonth> datas) {
    var type = "bar";
    var label = datas.get(0).getPriceType();
    var productBuyPrices = List.of(datas.get(0).getTotalPrice());
    var backgroundColor = SOLD_COLOR;
    var backgroundTouchColor = HOVER_SOLD_COLOR;

    return new StackedBarDataSet(
            type,
            label,
            backgroundColor,
            backgroundTouchColor,
            productBuyPrices
    );
  }

  private StackedBarDataSet<Double> mapToStackedBarProductBuyDataSet(List<ISoldAndBuyProductPriceByMonth> datas) {
    var type = "bar";
    var label = datas.get(1).getPriceType();
    var productBuyPrices = List.of(datas.get(1).getTotalPrice());
    var backgroundColor = BUY_COLOR;
    var backgroundTouchColor = HOVER_BUY_COLOR;


    return new StackedBarDataSet(
            type,
            label,
            backgroundColor,
            backgroundTouchColor,
            productBuyPrices
    );
  }
}
