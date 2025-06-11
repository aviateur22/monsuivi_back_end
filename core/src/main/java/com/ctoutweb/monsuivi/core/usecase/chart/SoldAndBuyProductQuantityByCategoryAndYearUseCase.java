package com.ctoutweb.monsuivi.core.usecase.chart;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;
import com.ctoutweb.monsuivi.core.usecase.base.IUseCase;
import com.ctoutweb.monsuivi.core.usecase.base.InputBase;
import com.ctoutweb.monsuivi.core.usecase.base.OutputBase;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.ISoldAndBuyProductQuantityByCategoryAndYearGateway;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.ISoldAndBuyProductQuantityByCategoryAndYearInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryAndYear.ISoldAndBuyProductQuantityByCategoryAndYearOutput;

/**
 * Récupération des données afin d'afficher les quantité sur l'achat et la vente de produit sur 1 année et par catégorie
 */
@CoreService
public class SoldAndBuyProductQuantityByCategoryAndYearUseCase implements IUseCase<SoldAndBuyProductQuantityByCategoryAndYearUseCase.Input, SoldAndBuyProductQuantityByCategoryAndYearUseCase.Output> {

  private final ISoldAndBuyProductQuantityByCategoryAndYearGateway soldAndBuyProductQuantityByCategoryAndYearGateway;
  private final CoreFactory coreFactory= InstanceLoader.getCoreFactory();

  public SoldAndBuyProductQuantityByCategoryAndYearUseCase(ISoldAndBuyProductQuantityByCategoryAndYearGateway soldAndBuyProductQuantityByCategoryAndYearGateway) {
    this.soldAndBuyProductQuantityByCategoryAndYearGateway = soldAndBuyProductQuantityByCategoryAndYearGateway;
  }

  @Override
  public Output execute(Input input) {
    long sellerId = input.getUsecaseInput().getSellerId();
    short yearRequest = input.getUsecaseInput().getYearRequest();

    if(!soldAndBuyProductQuantityByCategoryAndYearGateway.canSellerDisplayChartInformation(sellerId))
      throw new CoreException("Vous ne pouvez pas accéder aux informations graphique");

    // Validation du format du mois xx-yyyy exemple: 2021-21
    String regex = "^\\d{4}";
    if(!String.valueOf(yearRequest).matches(regex))
      throw new CoreException(String.format("Le format de l'année n'est pas valide: %s", yearRequest));

    // Récupération des données a afficher
    var datas = soldAndBuyProductQuantityByCategoryAndYearGateway.getSoldAndBuyProductQuantityByCategoryAndYearList(sellerId, yearRequest);

    // Renvoie de la réponse
    var output = coreFactory.getSoldAndBuyProductQuantityByCategoryAndYearOutputImpl(
            datas, yearRequest
    );

    return new Output(output);
  }

  public static class Input extends InputBase<ISoldAndBuyProductQuantityByCategoryAndYearInput> implements IUseCase.Input {
    public Input(ISoldAndBuyProductQuantityByCategoryAndYearInput inputPort) {
      super(inputPort);
    }
  }

  public static class Output extends OutputBase<ISoldAndBuyProductQuantityByCategoryAndYearOutput> implements IUseCase.Output {

    public Output(ISoldAndBuyProductQuantityByCategoryAndYearOutput outputBoundary) {
      super(outputBoundary);
    }
  }
}
