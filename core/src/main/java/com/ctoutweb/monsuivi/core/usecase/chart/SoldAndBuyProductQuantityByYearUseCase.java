package com.ctoutweb.monsuivi.core.usecase.chart;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;
import com.ctoutweb.monsuivi.core.usecase.base.IUseCase;
import com.ctoutweb.monsuivi.core.usecase.base.InputBase;
import com.ctoutweb.monsuivi.core.usecase.base.OutputBase;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.ISoldAndBuyProductQuantityByYearGateway;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.ISoldAndBuyProductQuantityByYearInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByYear.ISoldAndBuyProductQuantityByYearOutput;

@CoreService
public class SoldAndBuyProductQuantityByYearUseCase implements IUseCase<SoldAndBuyProductQuantityByYearUseCase.Input, SoldAndBuyProductQuantityByYearUseCase.Output> {

  private final ISoldAndBuyProductQuantityByYearGateway soldAndBuyProductQuantityByYearGateway;
  private final CoreFactory coreFactory = InstanceLoader.getCoreFactory();

  public SoldAndBuyProductQuantityByYearUseCase(ISoldAndBuyProductQuantityByYearGateway soldAndBuyProductQuantityByYearGateway) {
    this.soldAndBuyProductQuantityByYearGateway = soldAndBuyProductQuantityByYearGateway;
  }

  @Override
  public Output execute(Input input) {
    short yearRequest = input.getUsecaseInput().getYearRequest();
    long sellerId = input.getUsecaseInput().getSellerId();

    if(!soldAndBuyProductQuantityByYearGateway.canSellerDisplayChartInformation(sellerId))
      throw new CoreException("Vous ne pouvez pas accéder aux informations graphique");

    // Validation du format yyyy exemple: 2021
    String regex = "^\\d{4}";
    if(!String.valueOf(yearRequest).matches(regex))
      throw new CoreException(String.format("Le format de l'année n'est pas valide: %s", yearRequest));

    // Récupération des données a afficher
    var datas = soldAndBuyProductQuantityByYearGateway.getSoldAndBuyProductQuantityByYearList(sellerId, yearRequest);

    // Renvoie de la réponse
    var output = coreFactory.getSoldAndBuyProductQuantityByYearOutputImpl(
            datas, yearRequest
    );

    return new Output(output);
  }

  public static class Input extends InputBase<ISoldAndBuyProductQuantityByYearInput> implements IUseCase.Input {
    public Input(ISoldAndBuyProductQuantityByYearInput inputPort) {
      super(inputPort);
    }
  }

  public static class Output extends OutputBase<ISoldAndBuyProductQuantityByYearOutput> implements IUseCase.Output {
    public Output(ISoldAndBuyProductQuantityByYearOutput outputBoundary) {
      super(outputBoundary);
    }
  }
}
