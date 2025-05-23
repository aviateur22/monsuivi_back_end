package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyProductPriceBuyByCategoryAndYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByCategoryAndYear;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.ISoldAndBuyProductPriceBuyByCategoryAndYearInput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.dto.response.chart.SoldAndBuyProductPriceByCategoryAndYearResponseDto;
import com.ctoutweb.monsuivi.infra.model.message.chart.IChartDataResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoldAndBuyProductPriceByCategoryAndYearMapper {

  private final CoreFactory coreFactory;
  private final InfraFactory infraFactory;

  public SoldAndBuyProductPriceByCategoryAndYearMapper(CoreFactory coreFactory, InfraFactory infraFactory) {
    this.coreFactory = coreFactory;
    this.infraFactory = infraFactory;
  }

  /**
   * Map vers Input du useCase
   * @param sellerId long
   * @param year String
   * @return ISoldAndBuyProductPriceBuyByCategoryAndYearInput
   */
  public ISoldAndBuyProductPriceBuyByCategoryAndYearInput mapToInput(long sellerId, short year) {
    return coreFactory.getSoldAndBuyProductPriceBuyByCategoryAndYearInputImpl(sellerId, year);
  }

  /**
   * Map vers une liste ISoldAndBuyProductPriceByCategoryAndYear
   * @param extractDatas List<Object[]> - Données provenant de la DB
   * @return List<ISoldAndBuyProductPriceByCategoryAndYear>
   */
  public List<ISoldAndBuyProductPriceByCategoryAndYear> mapToSoldAndBuyProductPriceByCategoryAndYear(List<Object[]> extractDatas) {
    if(extractDatas == null || extractDatas.isEmpty())
      return List.of();

    return extractDatas.stream()
            .map(result -> coreFactory.getSoldAndBuyProductPriceByCategoryAndYearImpl(
                    (String) result[1], // Nom catgégory
                    (String) result[2], // backgroundColor
                    (String) result[3], // touchBackgroundColor
                    (String) result[4], // Annee
                    ((Number) result[5]).doubleValue(), // prix achat
                    ((Number) result[6]).doubleValue() // prix vendu
            ))
            .collect(Collectors.toList());
  }

  /**
   * Map vers le DTO
   * @param datas
   * @param yearRequest
   * @return
   */
  public SoldAndBuyProductPriceByCategoryAndYearResponseDto mapToDto(
          List<ISoldAndBuyProductPriceByCategoryAndYear> datas,
          short yearRequest) {
    var productBuyToDoughnutFormat = mapProductBuyToDoughnutFormat(datas, yearRequest);
    var productSoldToDoughnutFormat = mapProductSoldToDoughnutFormat(datas, yearRequest);

    return new SoldAndBuyProductPriceByCategoryAndYearResponseDto(
            productSoldToDoughnutFormat,
            productBuyToDoughnutFormat
    );
  }

  /**
   * Récupération des données d'achat et formate les données pour une sortie graphique de type Dougnhuts
   * @param datas List<ISoldAndBuyProductPriceByCategoryAndYear>
   * @param yearRequest String
   * @return IChartResponse
   */
  public IChartDataResponse mapProductBuyToDoughnutFormat(List<ISoldAndBuyProductPriceByCategoryAndYear> datas, short yearRequest) {
    var labels = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndYear::getProductCategoryName).toList();
    var productBuyPrices = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndYear::getBuyPriceProduct).toList();
    var backgroundColor = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndYear::getProductBackgroundColor).toList();
    var backgroundTouchColor = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndYear::getProductBackgroundTouchColor).toList();

    // Phrase décrivant les données affichées
    String responseMessage = String.format("Données sur vos produits achetés en %s", yearRequest);

    // Donnés pour affichage d'un grapphique de type Doughnut
    var buyProductDoughnutFormat = infraFactory.getChartJsDoughnut(
            labels,
            backgroundColor,
            backgroundTouchColor,
            productBuyPrices);

    return infraFactory.getChartDataResponseImpl(buyProductDoughnutFormat, responseMessage);
  }

  /**
   * Récupération des données de vente et formate les données pour une sortie graphique de type Dougnhuts
   * @param datas List<ISoldAndBuyProductPriceByCategoryAndYear>
   * @param yearRequest String
   * @return IChartResponse
   */
  public IChartDataResponse mapProductSoldToDoughnutFormat(List<ISoldAndBuyProductPriceByCategoryAndYear> datas, short yearRequest) {
    var labels = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndYear::getProductCategoryName).toList();
    var productSoldPrices = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndYear::getSoldPriceProduct).toList();
    var backgroundColor = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndYear::getProductBackgroundColor).toList();
    var backgroundTouchColor = datas.stream().map(ISoldAndBuyProductPriceByCategoryAndYear::getProductBackgroundTouchColor).toList();

    // Phrase décrivant les données affichées
    String responseMessage = String.format("Données sur vos produits vendus en %s", yearRequest);

    // Donnés pour affichage d'un grapphique de type Doughnut
    var soldProductDoughnutFormat = infraFactory.getChartJsDoughnut(
            labels,
            backgroundColor,
            backgroundTouchColor,
            productSoldPrices);

    return infraFactory.getChartDataResponseImpl(soldProductDoughnutFormat, responseMessage);
  }
}
