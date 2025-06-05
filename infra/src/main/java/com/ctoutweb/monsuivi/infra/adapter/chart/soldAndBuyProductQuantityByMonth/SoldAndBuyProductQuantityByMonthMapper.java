package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductQuantityByMonth;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByMonth;
import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByMonth;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.ISoldAndBuyProductPriceByMonthInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.ISoldAndBuyProductQuantityByMonthInput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.dto.response.chart.SoldAndBuyProductPriceByMonthDto;
import com.ctoutweb.monsuivi.infra.dto.response.chart.SoldAndBuyProductQuantityByMonthDto;
import com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.stackedBar.ChartJsStackedBar;
import com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.stackedBar.StackedBarDataSet;
import com.ctoutweb.monsuivi.infra.model.message.chart.IChartDataResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SoldAndBuyProductQuantityByMonthMapper {

  private final CoreFactory coreFactory;
  private final InfraFactory infraFactory;

  public SoldAndBuyProductQuantityByMonthMapper(CoreFactory coreFactory, InfraFactory infraFactory) {
    this.coreFactory = coreFactory;
    this.infraFactory = infraFactory;
  }
  public ISoldAndBuyProductQuantityByMonthInput getInput(long sellerId, short monthrequest, short yearRequest){
    return coreFactory.getSoldAndBuyProductQuantityByMonthInputImpl(sellerId, yearRequest, monthrequest);
  }

  public SoldAndBuyProductQuantityByMonthDto mapToDto(
          List<ISoldAndBuyProductQuantityByMonth> datas,
          String monthRequest,
          short yearRequest) {
    StackedBarDataSet<Double> buyProductQuantityDataSet = mapToStackedBarProductBuyDataSet(datas);
    StackedBarDataSet<Double> soldProductQuantityDataSet = mapToStackedBarProductSoldDataSet(datas);

    String axisLabelString = String.format("%s-%s", monthRequest, yearRequest);
    ChartJsStackedBar SoldAndBuyProductPriceByYearStackedBar =
            infraFactory.getStackedBar(
                    List.of(String.valueOf(axisLabelString)),
                    List.of(buyProductQuantityDataSet, soldProductQuantityDataSet)
            );

    IChartDataResponse chartDataResponse = infraFactory.getChartDataResponseImpl(
            SoldAndBuyProductPriceByYearStackedBar,
            String.format("Nombre d'article acheté et vendus pour %s %s", monthRequest, yearRequest));

    return new SoldAndBuyProductQuantityByMonthDto<>(chartDataResponse);
  }

  private StackedBarDataSet<Double> mapToStackedBarProductSoldDataSet(List<ISoldAndBuyProductQuantityByMonth> datas) {
    var type = "bar";
    var label = datas.get(0).getQuantityType();
    var productBuyPrices = List.of(datas.get(0).getTotalQuantity());
    var backgroundColor = "black";
    var backgroundTouchColor = "grey";

    return new StackedBarDataSet(
            type,
            label,
            backgroundColor,
            backgroundTouchColor,
            productBuyPrices
    );
  }

  private StackedBarDataSet<Double> mapToStackedBarProductBuyDataSet(List<ISoldAndBuyProductQuantityByMonth> datas) {
    var type = "bar";
    var label = datas.get(1).getQuantityType();
    var productBuyPrices = List.of(datas.get(1).getTotalQuantity());
    var backgroundColor = "orange";
    var backgroundTouchColor = "blue";


    return new StackedBarDataSet(
            type,
            label,
            backgroundColor,
            backgroundTouchColor,
            productBuyPrices
    );
  }
}
