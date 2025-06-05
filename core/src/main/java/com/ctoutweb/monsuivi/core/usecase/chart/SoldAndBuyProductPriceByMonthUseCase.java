package com.ctoutweb.monsuivi.core.usecase.chart;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductPriceByMonth;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;
import com.ctoutweb.monsuivi.core.model.usecase.IUseCase;
import com.ctoutweb.monsuivi.core.model.usecase.InputBase;
import com.ctoutweb.monsuivi.core.model.usecase.OutputBase;
import com.ctoutweb.monsuivi.core.port.chart.common.IBaseMonthGateway;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.ISoldAndBuyProductPriceByMonthGateway;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.ISoldAndBuyProductPriceByMonthInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductPriceByMonth.ISoldAndBuyProductPriceByMonthOutput;
import com.ctoutweb.monsuivi.core.util.NumberUtil;

@CoreService
public class SoldAndBuyProductPriceByMonthUseCase implements IUseCase<SoldAndBuyProductPriceByMonthUseCase.Input, SoldAndBuyProductPriceByMonthUseCase.Output> {

  private final ISoldAndBuyProductPriceByMonthGateway<ISoldAndBuyProductPriceByMonth> gateway;
  private final CoreFactory coreFactory = InstanceLoader.getCoreFactory();

  public SoldAndBuyProductPriceByMonthUseCase(ISoldAndBuyProductPriceByMonthGateway<ISoldAndBuyProductPriceByMonth> gateway) {
    this.gateway = gateway;
  }

  @Override
  public Output execute(Input input) {
    long sellerId = input.getUsecaseInput().getSellerId();
    short month = input.getUsecaseInput().getMonth();
    short year = input.getUsecaseInput().getYear();

    if(!gateway.canSellerDisplayChartInformation(sellerId))
      throw new CoreException("Vous ne pouvez pas accéder aux informations graphique");

    String monthRequested = String.format("%s-%02d", year, month);

    // Validation du format du mois xx-yyyy exemple: 2021-21
    String regex = "^\\d{4}-\\d{2}$";
    if(!monthRequested.matches(regex))
      throw new CoreException(String.format("Le format du mois n'est pas valide: %s", monthRequested));

    // Récupération des données a afficher
    var datas = gateway.getProductDataByMonth(sellerId, monthRequested);

    var output = coreFactory.getSoldAndBuyProductPriceByMonthOutputImpl(
      datas,
      NumberUtil.getMonthFromNumber(month),
      year
    );

    return new Output(output);
  }

  public static class Input extends InputBase<ISoldAndBuyProductPriceByMonthInput> implements IUseCase.Input {
    public Input(ISoldAndBuyProductPriceByMonthInput inputPort) {
      super(inputPort);
    }
  }

  public static class Output extends OutputBase<ISoldAndBuyProductPriceByMonthOutput> implements IUseCase.Output {
    public Output(ISoldAndBuyProductPriceByMonthOutput outputBoundary) {
      super(outputBoundary);
    }
  }
}
