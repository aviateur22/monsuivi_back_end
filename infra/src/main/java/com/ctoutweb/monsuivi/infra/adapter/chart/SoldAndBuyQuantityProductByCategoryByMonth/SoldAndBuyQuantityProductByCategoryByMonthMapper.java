package com.ctoutweb.monsuivi.infra.adapter.chart.SoldAndBuyQuantityProductByCategoryByMonth;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndMonth;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.ISoldAndBuyProductByCategoryByMonthInput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.dto.response.chart.SoldAndBuyQuantityProductByCategoryAndMonthResponseDto;
import com.ctoutweb.monsuivi.infra.model.message.chart.IChartDataResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoldAndBuyQuantityProductByCategoryByMonthMapper {

  private final CoreFactory coreFactory;
  private final InfraFactory infraFactory;

  public SoldAndBuyQuantityProductByCategoryByMonthMapper(CoreFactory coreFactory, InfraFactory infraFactory) {
    this.coreFactory = coreFactory;
    this.infraFactory = infraFactory;
  }

  /**
   * Map vers input du useCase
   * @param sellerId long - vendeur
   * @param month short - mois
   * @param year shirt - années
   * @return ISoldAndBuyProductByCategoryByMonthInput
   */
  public ISoldAndBuyProductByCategoryByMonthInput getInput(long sellerId, short month, short year) {
    return coreFactory.getSoldAndBuyProductByCategoryByMonthInputImpl(sellerId, month, year);
  }


  /**
   * Renvoie les données formaté pour  ISoldAndBuyProductQuantityByCategoryAndMonth
   *
   * @param extractDatas List<Object[] - Données en provenance de la DB
   * @return List<ISoldAndBuyProductQuantityByCategoryAndMonth>
   */
  public List<ISoldAndBuyProductQuantityByCategoryAndMonth> mapToSoldAndBuyProductQuantityByCategoryAndMonth(
          List<Object[]> extractDatas) {

    if(extractDatas == null || extractDatas.isEmpty())
      return List.of();

      return extractDatas.stream()
          .map(result -> coreFactory.getSoldAndBuyProductQuantityByCategoryAndMonthImpl(
                  (String) result[1], // Nom catgégory
                  (String) result[2], // backgroundColor
                  (String) result[3], // touchBackgroundColor
                  ((Number) result[5]).intValue(), // quantité achat
                  ((Number) result[6]).intValue() // quantité vendu
          ))
          .collect(Collectors.toList());
  }


  /**
   * Préparation des données afin de
   * @param datas
   * @param monthRequest
   * @param yearRequest
   * @return
   */
  public SoldAndBuyQuantityProductByCategoryAndMonthResponseDto mapToDto(
          List<ISoldAndBuyProductQuantityByCategoryAndMonth> datas,
          String monthRequest,
          String yearRequest) {

    var productBuyToDoughnutFormat = mapProductBuyToDoughnutFormat(datas, monthRequest, yearRequest);
    var productSoldToDoughnutFormat = mapProductSoldToDoughnutFormat(datas, monthRequest, yearRequest);

    return new SoldAndBuyQuantityProductByCategoryAndMonthResponseDto(productSoldToDoughnutFormat, productBuyToDoughnutFormat);
  }

  /**
   * Format les données d'achat au format graphique Doughnut
   * @param datas List<ISoldAndBuyProductQuantityByCategoryAndMonth>
   * @return IChart
   */
  public IChartDataResponse mapProductBuyToDoughnutFormat(
          List<ISoldAndBuyProductQuantityByCategoryAndMonth> datas,
          String monthRequest,
          String yearRequest) {

    // Formattage des données pour
    var labels = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndMonth::getProductCategoryName).toList();
    var productBuyQuantities = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndMonth::getBuyQuantityProduct).toList();
    var backgroundColor = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndMonth::getProductBackgroundColor).toList();
    var backgroundTouchColor = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndMonth::getProductBackgroundTouchColor).toList();

    // Phrase décrivant les données affichées
    String responseMessage = String.format("Données sur vos produits achetés en %s %s", monthRequest, yearRequest);

    // Donnés pour affichage d'un grapphique de type Doughnut
    var buyProductDoughnutFormat = infraFactory.getChartJsDoughnut(labels, backgroundColor, backgroundTouchColor, productBuyQuantities);
    return  infraFactory.getChartDataResponseImpl(buyProductDoughnutFormat, responseMessage);
  }

  /**
   *  Format les données de vente au format graphique Doughnut
   * @param datas List<ISoldAndBuyProductQuantityByCategoryAndMonth>
   * @return IChart
   */
  public IChartDataResponse mapProductSoldToDoughnutFormat(
          List<ISoldAndBuyProductQuantityByCategoryAndMonth> datas,
          String monthRequest,
          String yearRequest) {

    // Formattage des données pour
    var labels = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndMonth::getProductCategoryName).toList();
    var productSoldQuantities = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndMonth::getSoldQuantityProduct).toList();
    var backgroundColor = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndMonth::getProductBackgroundColor).toList();
    var backgroundTouchColor = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndMonth::getProductBackgroundTouchColor).toList();

    // Phrase décrivant les données affichées
    String responseMessage = String.format("Données sur vos produits vendus en %s %s", monthRequest, yearRequest);

    // Donnés pour affichage d'un grapphique de type Doughnut
    var soldProductDoughnutFormat = infraFactory.getChartJsDoughnut(labels, backgroundColor, backgroundTouchColor, productSoldQuantities);
    return infraFactory.getChartDataResponseImpl(soldProductDoughnutFormat, responseMessage);
  }
}
