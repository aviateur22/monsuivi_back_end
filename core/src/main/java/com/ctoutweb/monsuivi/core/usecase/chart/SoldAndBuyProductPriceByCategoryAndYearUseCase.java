package com.ctoutweb.monsuivi.core.usecase.chart;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;
import com.ctoutweb.monsuivi.core.usecase.base.IUseCase;
import com.ctoutweb.monsuivi.core.usecase.base.InputBase;
import com.ctoutweb.monsuivi.core.usecase.base.OutputBase;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.ISoldAndBuyProductPriceBuyByCategoryAndYearInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.ISoldAndBuyProductPriceBuyByCategoryAndYearOutput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndYear.ISoldAndBuyProductPriceByCategoryAndYearGateway;

/**
 * Récupération des données afin d'afficher les prix sur l'achat et la vente de produit sur 1 année et par catégorie
 */
@CoreService
public class SoldAndBuyProductPriceByCategoryAndYearUseCase implements IUseCase<SoldAndBuyProductPriceByCategoryAndYearUseCase.Input, SoldAndBuyProductPriceByCategoryAndYearUseCase.Output> {

  private final ISoldAndBuyProductPriceByCategoryAndYearGateway soldAndBuyProductPriceByCategoryAndYearGateway;
  private final CoreFactory coreFactory = InstanceLoader.getCoreFactory();

  public SoldAndBuyProductPriceByCategoryAndYearUseCase(ISoldAndBuyProductPriceByCategoryAndYearGateway soldAndBuyProductPriceByCategoryAndYearGateway) {
    this.soldAndBuyProductPriceByCategoryAndYearGateway = soldAndBuyProductPriceByCategoryAndYearGateway;
  }

  @Override
  public Output execute(Input input) {
    long sellerId = input.getUsecaseInput().getSellerId();
    short yearRequest = input.getUsecaseInput().getYearRequest();

    if(!soldAndBuyProductPriceByCategoryAndYearGateway.canSellerDisplayChartInformation(sellerId))
      throw new CoreException("Vous ne pouvez pas accéder aux informations graphique");

    // Validation du format yyyy exemple: 2021
    String regex = "^\\d{4}";
    if(!String.valueOf(yearRequest).matches(regex))
      throw new CoreException(String.format("Le format de l'année n'est pas valide: %s", yearRequest));

    // Récupération des données a afficher
    var datas = soldAndBuyProductPriceByCategoryAndYearGateway.getSoldAndBuyProductPriceByCategoryAndYearList(sellerId, yearRequest);

    // Renvoie de la réponse
    var output = coreFactory.getSoldAndBuyProductPriceBuyByCategoryAndYearOutputImpl(
            datas, yearRequest
    );

    return new Output(output);
  }

  public static class Input extends InputBase<ISoldAndBuyProductPriceBuyByCategoryAndYearInput> implements IUseCase.Input {

    public Input(ISoldAndBuyProductPriceBuyByCategoryAndYearInput inputPort) {
      super(inputPort);
    }
  }

  public static class Output extends OutputBase<ISoldAndBuyProductPriceBuyByCategoryAndYearOutput> implements IUseCase.Output {

    public Output(ISoldAndBuyProductPriceBuyByCategoryAndYearOutput outputBoundary) {
      super(outputBoundary);
    }
  }
}
