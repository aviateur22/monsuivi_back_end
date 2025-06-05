package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyPriceProductByCategoryAndMonth;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndMonth;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.ISoldAndBuyProductPriceByCategoryAndMonthInput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.dto.response.chart.SoldAndBuyProductPriceByCategoryAndMonthResponseDto;
import com.ctoutweb.monsuivi.infra.model.message.chart.IChartDataResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoldAndBuyProductPriceByCategoryAndMonthMapper {
  private final CoreFactory coreFactory;
  private final InfraFactory infraFactory;
  public SoldAndBuyProductPriceByCategoryAndMonthMapper(CoreFactory coreFactory, InfraFactory infraFactory) {
    this.coreFactory = coreFactory;
    this.infraFactory = infraFactory;
  }

  /**
   * map vers input du useCase
   * @param sellerId Long
   * @param month - short
   * @param year - short
   * @return ISoldAndBuyProductPriceByCategoryAndMonthInput
   */
  public ISoldAndBuyProductPriceByCategoryAndMonthInput getInput(long sellerId, short month, short year) {
    return coreFactory.getSoldAndBuyProductPriceByCategoryAndMonthInputImpl(sellerId, month, year);
  }

  /**
   * Map les données de la DB vers ISoldAndBuyProductPriceByCategoryAndMonth
   * @param extractDatas List<Object[]>
   * @return  List<ISoldAndBuyProductPriceByCategoryAndMonth>
   */
  public List<ISoldAndBuyProductPriceByCategoryAndMonth> mapToSoldAndBuyProductPriceByCategoryAndMonth(List<Object[]> extractDatas) {
    if(extractDatas == null || extractDatas.isEmpty())
      return List.of();

    return extractDatas.stream()
            .map(result -> coreFactory.getSoldAndBuyProductPriceByCategoryAndMonth(
                    (String) result[1], // Nom catgégory
                    (String) result[2], // backgroundColor
                    (String) result[3], // touchBackgroundColor
                    ((Number) result[5]).doubleValue(), // prix achat
                    ((Number) result[6]).doubleValue() // prix vendu
            ))
            .collect(Collectors.toList());
  }

  /**
   * Map vers le DTO
   * @param datas List<ISoldAndBuyProductPriceByCategoryAndMonth>
   * @param monthRequest String - Mois en lettre
   * @param yearRequest String - Années
   * @return SoldAndBuyProductPriceByCategoryAndMonthDto
   */
  public SoldAndBuyProductPriceByCategoryAndMonthResponseDto mapToDto(
          List<ISoldAndBuyProductPriceByCategoryAndMonth> datas,
          String monthRequest,
          String yearRequest) {

    var productBuyToDoughnutFormat = mapProductBuyToDoughnutFormat(datas, monthRequest, yearRequest);
    var productSoldToDoughnutFormat = mapProductSoldToDoughnutFormat(datas, monthRequest, yearRequest);

    return new SoldAndBuyProductPriceByCategoryAndMonthResponseDto(productSoldToDoughnutFormat, productBuyToDoughnutFormat);
  }

  /**
   * Format les données d'achat au format graphique Doughnut
   * @param datas List<ISoldAndBuyProductQuantityByCategoryAndMonth>
   * @return IChart
   */
  public IChartDataResponse mapProductBuyToDoughnutFormat(
          List<ISoldAndBuyProductPriceByCategoryAndMonth> datas,
          String monthRequest,
          String yearRequest) {

    // Formattage des données pour
    var labels = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndMonth::getProductCategoryName).toList();
    var productBuyPrices = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndMonth::getBuyPriceProduct).toList();
    var backgroundColor = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndMonth::getProductBackgroundColor).toList();
    var backgroundTouchColor = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndMonth::getProductBackgroundTouchColor).toList();

    // Phrase décrivant les données affichées
    String responseMessage = String.format("Dépense par catégorie en %s %s", monthRequest, yearRequest);

    // Donnés pour affichage d'un graphique de type Doughnut
    var buyProductDoughnutFormat = infraFactory.getChartJsDoughnut(labels, backgroundColor, backgroundTouchColor, productBuyPrices);

    return  infraFactory.getChartDataResponseImpl(buyProductDoughnutFormat, responseMessage);
  }

  /**
   *  Format les données de vente au format graphique Doughnut
   * @param datas List<ISoldAndBuyProductQuantityByCategoryAndMonth>
   * @return IChart
   */
  public IChartDataResponse mapProductSoldToDoughnutFormat(
          List<ISoldAndBuyProductPriceByCategoryAndMonth> datas,
          String monthRequest,
          String yearRequest) {

    // Formattage des données pour
    var labels = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndMonth::getProductCategoryName).toList();
    var productSoldPrices = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndMonth::getSoldPriceProduct).toList();
    var backgroundColor = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndMonth::getProductBackgroundColor).toList();
    var backgroundTouchColor = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndMonth::getProductBackgroundTouchColor).toList();

    // Phrase décrivant les données affichées
    String responseMessage = String.format("Vente par catégorie en %s %s", monthRequest, yearRequest);

    // Donnés pour affichage d'un grapphique de type Doughnut
    var soldProductDoughnutFormat = infraFactory.getChartJsDoughnut(labels, backgroundColor, backgroundTouchColor, productSoldPrices);
    return infraFactory.getChartDataResponseImpl(soldProductDoughnutFormat, responseMessage);
  }
}
