package com.ctoutweb.monsuivi.core.usecase.chart;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;
import com.ctoutweb.monsuivi.core.model.usecase.IUseCase;
import com.ctoutweb.monsuivi.core.model.usecase.InputBase;
import com.ctoutweb.monsuivi.core.model.usecase.OutputBase;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.ISoldAndBuyProductByCategoryByMonthInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.ISoldAndBuyProductByCategoryByMonthOutput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByCategoryByMonth.ISoldAndBuyQuantityProductByCategoryByMonthGateway;
import com.ctoutweb.monsuivi.core.util.NumberUtil;

@CoreService
public class SoldAndBuyProductQuantityByCategoryMonthUseCase implements IUseCase<SoldAndBuyProductQuantityByCategoryMonthUseCase.Input, SoldAndBuyProductQuantityByCategoryMonthUseCase.Output> {

  private final ISoldAndBuyQuantityProductByCategoryByMonthGateway chartSoldAndBuyQuantityProductByMonthGateway;
  private final CoreFactory coreFactory = InstanceLoader.getCoreFactory();

  public SoldAndBuyProductQuantityByCategoryMonthUseCase(ISoldAndBuyQuantityProductByCategoryByMonthGateway chartSoldAndBuyQuantityProductByMonthGateway) {
    this.chartSoldAndBuyQuantityProductByMonthGateway = chartSoldAndBuyQuantityProductByMonthGateway;
  }

  @Override
  public Output execute(Input input) {
    short month = input.getUsecaseInput().getMonth();
    short year = input.getUsecaseInput().getYear();

    String monthRequested = String.format("%s-%02d", year, month);
    long sellerId = input.getUsecaseInput().getSellerId();

    if(!chartSoldAndBuyQuantityProductByMonthGateway.canSellerDisplayChartInformation(sellerId))
      throw new CoreException("Vous ne pouvez pas accéder aux informations graphique");

    // Validation du format du mois xx-yyyy exemple: 2021-21
    String regex = "^\\d{4}-\\d{2}$";
    if(!monthRequested.matches(regex))
      throw new CoreException(String.format("Le format du mois n'est pas valide: %s", monthRequested));

    // Récupération des données a afficher
    var datas = chartSoldAndBuyQuantityProductByMonthGateway.getSoldAndBuyProductQuantityByCategoryAndMonthList(sellerId, monthRequested);

    ISoldAndBuyProductByCategoryByMonthOutput output = coreFactory.getChartByProductTypeMonthOutputImpl(
            datas,
            year,
            NumberUtil.getMonthFromNumber(month)
    );

    return new Output(output);
  }

  public static class Input extends InputBase<ISoldAndBuyProductByCategoryByMonthInput> implements IUseCase.Input {
    public Input(ISoldAndBuyProductByCategoryByMonthInput inputPort) {
      super(inputPort);
    }
  }

  public static class Output extends OutputBase<ISoldAndBuyProductByCategoryByMonthOutput> implements IUseCase.Output {

    public Output(ISoldAndBuyProductByCategoryByMonthOutput outputBoundary) {
      super(outputBoundary);
    }
  }
}
