package com.ctoutweb.monsuivi.core.usecase.chart;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.usecase.base.IUseCase;
import com.ctoutweb.monsuivi.core.usecase.base.InputBase;
import com.ctoutweb.monsuivi.core.usecase.base.OutputBase;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.ISoldAndBuyProductPriceByCategoryAndMonthGateway;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.ISoldAndBuyProductPriceByCategoryAndMonthInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByCategoryAndMonth.ISoldAndBuyProductPriceByCategoryAndMonthOutput;
import com.ctoutweb.monsuivi.core.util.NumberUtil;

/**
 * Récupération données pour affichage des prix de vente et achat par catégorie produit et mois
 */
@CoreService
public class SoldAndBuyProductPriceByCategoryAndMonthUseCase implements IUseCase<SoldAndBuyProductPriceByCategoryAndMonthUseCase.Input, SoldAndBuyProductPriceByCategoryAndMonthUseCase.Output> {
  private final ISoldAndBuyProductPriceByCategoryAndMonthGateway soldAndBuyProductPriceByCategoryAndMonthGateway;
  private final CoreFactory coreFactory;
  public SoldAndBuyProductPriceByCategoryAndMonthUseCase(
          ISoldAndBuyProductPriceByCategoryAndMonthGateway soldAndBuyProductPriceByCategoryAndMonthGateway,
          CoreFactory coreFactory) {
    this.soldAndBuyProductPriceByCategoryAndMonthGateway = soldAndBuyProductPriceByCategoryAndMonthGateway;
    this.coreFactory = coreFactory;
  }

  @Override
  public Output execute(Input input) {
    short month = input.getUsecaseInput().getMonth();
    short year = input.getUsecaseInput().getYear();

    String monthRequested = String.format("%s-%02d", year, month);
    long sellerId = input.getUsecaseInput().getSellerId();

    if(!soldAndBuyProductPriceByCategoryAndMonthGateway.canSellerDisplayChartInformation(sellerId))
      throw new CoreException("Vous ne pouvez pas accéder aux informations graphique");

    // Validation du format du mois xx-yyyy exemple: 2021-21
    String regex = "^\\d{4}-\\d{2}$";
    if(!monthRequested.matches(regex))
      throw new CoreException(String.format("Le format du mois n'est pas valide: %s", monthRequested));

    // Récupération des données a afficher
    var datas = soldAndBuyProductPriceByCategoryAndMonthGateway.getSoldAndBuyProductPriceByCategoryAndMonthList(sellerId, monthRequested);

    var output = coreFactory.getSoldAndBuyProductPriceByCategoryAndMonthOutputImpl(
            datas,
            year,
            NumberUtil.getMonthFromNumber(month)
    );

    return new SoldAndBuyProductPriceByCategoryAndMonthUseCase.Output(output);
  }

  public static class Input extends InputBase<ISoldAndBuyProductPriceByCategoryAndMonthInput> implements IUseCase.Input {
    public Input(ISoldAndBuyProductPriceByCategoryAndMonthInput inputPort) {
      super(inputPort);
    }
  }

  public static class Output extends OutputBase<ISoldAndBuyProductPriceByCategoryAndMonthOutput> implements IUseCase.Output {

    public Output(ISoldAndBuyProductPriceByCategoryAndMonthOutput outputBoundary) {
      super(outputBoundary);
    }
  }

}
