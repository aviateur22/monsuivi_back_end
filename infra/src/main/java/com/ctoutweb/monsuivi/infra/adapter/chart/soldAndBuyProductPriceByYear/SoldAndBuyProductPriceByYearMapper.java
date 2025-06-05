package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductPriceByYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByYear;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.ISoldAndBuyProductPriceByYearInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.ISoldAndBuyProductPriceByYearOutput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.dto.response.chart.SoldAndBuyProductPriceByYearResponseDto;
import com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.stackedBar.ChartJsStackedBar;
import com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.stackedBar.StackedBarDataSet;
import com.ctoutweb.monsuivi.infra.model.message.chart.IChartDataResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoldAndBuyProductPriceByYearMapper {
  private final CoreFactory coreFactory;
  private final InfraFactory infraFactory;

  public SoldAndBuyProductPriceByYearMapper(CoreFactory coreFactory, InfraFactory infraFactory) {
    this.coreFactory = coreFactory;
    this.infraFactory = infraFactory;
  }

  /**
   * Map vers input
   * @param sellerId
   * @param yearRequest
   * @return ISoldAndBuyProductPriceByYearInput
   */
  public ISoldAndBuyProductPriceByYearInput getInput(long sellerId, short yearRequest) {
    return coreFactory.getSoldAndBuyProductPriceByYearInputImpl(sellerId, yearRequest);
  }

  /**
   * Map vers output
   * @param datas
   * @param requestYear
   * @return ISoldAndBuyProductPriceByYearOutput
   */
  public ISoldAndBuyProductPriceByYearOutput getOutput(List<ISoldAndBuyProductPriceByYear> datas, short requestYear) {
    return coreFactory.getSoldAndBuyProductPriceByYearOutputImpl(datas, requestYear);
  }

  /**
   * Map vers le DTO
   * @param datas List<ISoldAndBuyProductPriceByYear>
   * @return SoldAndBuyProductPriceByYearResponseDto
   */
  public SoldAndBuyProductPriceByYearResponseDto mapToDto(List<ISoldAndBuyProductPriceByYear> datas, short yearRequest) {
    StackedBarDataSet<Double> buyProductPriceDataSet = mapToStackedBarProductBuyDataSet(datas) ;
    StackedBarDataSet<Double> soldProductPriceDataSet = mapToStackedBarProductSoldDataSet(datas);

    ChartJsStackedBar SoldAndBuyProductPriceByYearStackedBar =
            infraFactory.getStackedBar(
                    List.of(String.valueOf(yearRequest)),
                    List.of(buyProductPriceDataSet, soldProductPriceDataSet)
            );

    IChartDataResponse chartDataResponse = infraFactory.getChartDataResponseImpl(
            SoldAndBuyProductPriceByYearStackedBar,
            ""
    );
    return new SoldAndBuyProductPriceByYearResponseDto<>(chartDataResponse);
  }

  /**
   * Map vers
   * @param extractDatas
   * @return
   */
  public List<ISoldAndBuyProductPriceByYear> mapToSoldAndBuyProductPriceByYearList(List<Object[]> extractDatas) {
    if(extractDatas == null || extractDatas.isEmpty())
      return List.of();

    return extractDatas.stream()
            .map(result -> coreFactory.getSoldAndBuyProductPriceByYearImpl(
                    ((Number) result[0]).intValue(), // prix total
                    (String) result[1], // Type de prix (Achat ou vente)
                    String.valueOf(((Number) result[2]).intValue()) // année
            ))
            .collect(Collectors.toList());
  }


  public StackedBarDataSet mapToStackedBarProductBuyDataSet(List<ISoldAndBuyProductPriceByYear> datas) {
    var type = "bar";
    var label = datas.get(0).getPriceType();
    var productBuyPrices = List.of(datas.get(0).getTotalPrice());
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

  public StackedBarDataSet mapToStackedBarProductSoldDataSet(List<ISoldAndBuyProductPriceByYear> datas) {
    var type = "bar";
    var label = datas.get(1).getPriceType();
    var productBuyPrices = List.of(datas.get(1).getTotalPrice());
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
