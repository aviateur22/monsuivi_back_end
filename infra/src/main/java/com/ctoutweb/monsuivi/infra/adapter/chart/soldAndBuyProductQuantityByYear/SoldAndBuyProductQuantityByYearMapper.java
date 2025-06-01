package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductQuantityByYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByYear;
import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByYear;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.ISoldAndBuyProductQuantityByYearInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.ISoldAndBuyProductQuantityByYearOutput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.dto.response.chart.SoldAndBuyProductPriceByYearResponseDto;
import com.ctoutweb.monsuivi.infra.dto.response.chart.SoldAndBuyQuantityProductByYearResponseDto;
import com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.stackedBar.ChartJsStackedBar;
import com.ctoutweb.monsuivi.infra.model.chart.dataset.chartjs.stackedBar.StackedBarDataSet;
import com.ctoutweb.monsuivi.infra.model.message.chart.IChartDataResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoldAndBuyProductQuantityByYearMapper {
  private final CoreFactory coreFactory;
  private final InfraFactory infraFactory;

  public SoldAndBuyProductQuantityByYearMapper(CoreFactory coreFactory, InfraFactory infraFactory) {
    this.coreFactory = coreFactory;
    this.infraFactory = infraFactory;
  }

  /**
   * Map vers input
   * @param sellerId
   * @param yearRequest
   * @return
   */
  public ISoldAndBuyProductQuantityByYearInput getInput(long sellerId, short yearRequest) {
    return coreFactory.getSoldAndBuyProductQuantityByYearInputImpl(sellerId, yearRequest);
  }

  /**
   * Map vers output
   * @param datas
   * @param requestYear
   * @return
   */
  public ISoldAndBuyProductQuantityByYearOutput getOutput(List<ISoldAndBuyProductQuantityByYear> datas, short requestYear) {
    return coreFactory.getSoldAndBuyProductQuantityByYearOutputImpl(datas, requestYear);
  }

  /**
   * mapToSoldAndBuyProductQuantity
   * @param extractDatas
   * @return
   */
  public List<ISoldAndBuyProductQuantityByYear> mapToSoldAndBuyProductQuantity(List<Object[]> extractDatas) {
    if(extractDatas == null || extractDatas.isEmpty())
      return List.of();

    return extractDatas.stream()
            .map(result -> coreFactory.getSoldAndBuyProductQuantityByYearImpl(
                    ((Number) result[0]).intValue(), // Quantité vendu
                    (String) result[1], // Type de quantité (Achat ou vente)
                    String.valueOf(((Number) result[2]).intValue()) // année
            ))
            .collect(Collectors.toList());
  }

  /**
   *
   * @param datas
   * @param yearRequest
   * @return
   */
  public SoldAndBuyQuantityProductByYearResponseDto mapToDto(List<ISoldAndBuyProductQuantityByYear> datas, short yearRequest) {
    StackedBarDataSet<Double> buyProductQuantityDataSet = mapToStackedBarProductBuyDataSet(datas) ;
    StackedBarDataSet<Double> soldProductQuantityDataSet = mapToStackedBarProductSoldDataSet(datas);

    ChartJsStackedBar SoldAndBuyProductPriceByYearStackedBar =
            infraFactory.getStackedBar(
                    List.of(String.valueOf(yearRequest)),
                    List.of(buyProductQuantityDataSet, soldProductQuantityDataSet)
            );

    IChartDataResponse chartDataResponse = infraFactory.getChartDataResponseImpl(
            SoldAndBuyProductPriceByYearStackedBar,
            ""
    );

    return new SoldAndBuyQuantityProductByYearResponseDto<>(chartDataResponse);
  }

  private StackedBarDataSet<Double> mapToStackedBarProductSoldDataSet(List<ISoldAndBuyProductQuantityByYear> datas) {
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

  private StackedBarDataSet<Double> mapToStackedBarProductBuyDataSet(List<ISoldAndBuyProductQuantityByYear> datas) {
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
