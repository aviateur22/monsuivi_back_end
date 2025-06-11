package com.ctoutweb.monsuivi.core.usecase.chart;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;
import com.ctoutweb.monsuivi.core.usecase.base.IUseCase;
import com.ctoutweb.monsuivi.core.usecase.base.InputBase;
import com.ctoutweb.monsuivi.core.usecase.base.OutputBase;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.ISoldAndBuyProductPriceByYearGateway;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.ISoldAndBuyProductPriceByYearInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByYear.ISoldAndBuyProductPriceByYearOutput;

/**
 * Récupération des données afin d'afficher les données sur l'achat et la vente de produit sur 1 année
 */
@CoreService
public class SoldAndBuyProductPriceByYearUseCase implements IUseCase<SoldAndBuyProductPriceByYearUseCase.Input, SoldAndBuyProductPriceByYearUseCase.Output> {

  private final ISoldAndBuyProductPriceByYearGateway soldAndBuyProductPriceByYearGateway;
  private final CoreFactory coreFactory = InstanceLoader.getCoreFactory();

  public SoldAndBuyProductPriceByYearUseCase(ISoldAndBuyProductPriceByYearGateway soldAndBuyProductPriceByYearGateway) {
    this.soldAndBuyProductPriceByYearGateway = soldAndBuyProductPriceByYearGateway;
  }
  @Override
  public Output execute(Input input) {
    short yearRequest = input.getUsecaseInput().getYearRequest();
    long sellerId = input.getUsecaseInput().getSellerId();

    if(!soldAndBuyProductPriceByYearGateway.canSellerDisplayChartInformation(sellerId))
      throw new CoreException("Vous ne pouvez pas accéder aux informations graphique");

    // Validation du format yyyy exemple: 2021
    String regex = "^\\d{4}";
    if(!String.valueOf(yearRequest).matches(regex))
      throw new CoreException(String.format("Le format de l'année n'est pas valide: %s", yearRequest));

    // Récupération des données a afficher
    var datas = soldAndBuyProductPriceByYearGateway.getSoldAndBuyProductPriceByYearList(sellerId, yearRequest);

    // Renvoie de la réponse
    var output = coreFactory.getSoldAndBuyProductPriceByYearOutputImpl(
            datas, yearRequest
    );

    return new Output(output);
  }

  public static class Input extends InputBase<ISoldAndBuyProductPriceByYearInput> implements IUseCase.Input {
    public Input(ISoldAndBuyProductPriceByYearInput input) {
      super(input);
    }
  }

  public static class Output extends OutputBase<ISoldAndBuyProductPriceByYearOutput> implements IUseCase.Output {
    public Output(ISoldAndBuyProductPriceByYearOutput outputBoundary) {
      super(outputBoundary);
    }
  }
}
