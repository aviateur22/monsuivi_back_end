package com.ctoutweb.monsuivi.infra.adapter.chart.soldAndBuyQuantityProductByCategoryAndYear;

import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByCategoryAndYear;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.ISoldAndBuyProductQuantityByCategoryAndYearInput;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.dto.response.chart.SoldAndBuyProductQuantityByCategoryAndYearResponseDto;
import com.ctoutweb.monsuivi.infra.model.message.chart.IChartDataResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoldAndBuyProductQuantityByCategoryAndYearMapper {
  private final CoreFactory coreFactory;
  private final InfraFactory infraFactory;

  public SoldAndBuyProductQuantityByCategoryAndYearMapper(CoreFactory coreFactory, InfraFactory infraFactory) {
    this.coreFactory = coreFactory;
    this.infraFactory = infraFactory;
  }

  /**
   * Map vers Input du useCase
   * @param sellerId long
   * @param year String
   * @return ISoldAndBuyProductQuantityByCategoryAndYearInput
   */
  public ISoldAndBuyProductQuantityByCategoryAndYearInput mapToInput(long sellerId, short year) {
    return coreFactory.getSoldAndBuyProductQuantityByCategoryAndYearInputImpl(sellerId, year);
  }

  /**
   * Map les données de la db vers List<ISoldAndBuyProductQuantityByCategoryAndYear>
   * @param extractDatas - List<Object[]> données en provenance de la DB
   * @return List<ISoldAndBuyProductQuantityByCategoryAndYear>
   */
  public List<ISoldAndBuyProductQuantityByCategoryAndYear> mapToSoldAndBuyProductQuantityByCategoryAndYear(List<Object[]> extractDatas) {
    if(extractDatas == null || extractDatas.isEmpty())
      return List.of();

    return extractDatas.stream()
            .map(result -> coreFactory.getSoldAndBuyProductQuantityByCategoryAndYearImpl(
                    (String) result[1], // Nom catgégory
                    (String) result[2], // backgroundColor
                    (String) result[3], // touchBackgroundColor
                    (String) result[4], // Annee
                    ((Number) result[5]).intValue(), // prix achat
                    ((Number) result[6]).intValue() // prix vendu
            ))
            .collect(Collectors.toList());
  }

  /**
   * Map vers le DTO
   * @param datas List<ISoldAndBuyProductQuantityByCategoryAndYear>
   * @param yearRequest short
   * @return SoldAndBuyProductQuantityByCategoryAndYearResponseDto
   */
  public SoldAndBuyProductQuantityByCategoryAndYearResponseDto mapToDto(
          List<ISoldAndBuyProductQuantityByCategoryAndYear> datas,
          short yearRequest) {
    var productBuyToDoughnutFormat = mapProductBuyToDoughnutFormat(datas, yearRequest);
    var productSoldToDoughnutFormat = mapProductSoldToDoughnutFormat(datas, yearRequest);

    return new SoldAndBuyProductQuantityByCategoryAndYearResponseDto(
            productSoldToDoughnutFormat,
            productBuyToDoughnutFormat
    );
  }

  /**
   * Récupération des données d'achat et formate les données pour une sortie graphique de type Dougnhuts
   * @param datas List<ISoldAndBuyProductQuantityByCategoryAndYear>
   * @param yearRequest String
   * @return IChartResponse
   */
  public IChartDataResponse mapProductBuyToDoughnutFormat(List<ISoldAndBuyProductQuantityByCategoryAndYear> datas, short yearRequest) {
    var labels = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndYear::getProductCategoryName).toList();
    var productBuyPrices = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndYear::getBuyQuantityProduct).toList();
    var backgroundColor = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndYear::getProductBackgroundColor).toList();
    var backgroundTouchColor = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndYear::getProductBackgroundTouchColor).toList();

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
   * @param datas List<ISoldAndBuyProductQuantityByCategoryAndYear>
   * @param yearRequest String
   * @return IChartResponse
   */
  public IChartDataResponse mapProductSoldToDoughnutFormat(List<ISoldAndBuyProductQuantityByCategoryAndYear> datas, short yearRequest) {
    var labels = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndYear::getProductCategoryName).toList();
    var productSoldPrices = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndYear::getSoldQuantityProduct).toList();
    var backgroundColor = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndYear::getProductBackgroundColor).toList();
    var backgroundTouchColor = datas.stream().map(ISoldAndBuyProductQuantityByCategoryAndYear::getProductBackgroundTouchColor).toList();

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
