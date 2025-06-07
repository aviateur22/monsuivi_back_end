package com.ctoutweb.monsuivi.core.usecase.chart;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.entity.chart.ISoldAndBuyProductQuantityByMonth;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;
import com.ctoutweb.monsuivi.core.model.usecase.IUseCase;
import com.ctoutweb.monsuivi.core.model.usecase.InputBase;
import com.ctoutweb.monsuivi.core.model.usecase.OutputBase;
import com.ctoutweb.monsuivi.core.port.chart.common.IBaseMonthGateway;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.ISoldAndBuyProductQuantityByMonthGateway;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.ISoldAndBuyProductQuantityByMonthInput;
import com.ctoutweb.monsuivi.core.port.chart.soldAndBuyProductQuantityByMonth.ISoldAndBuyProductQuantityByMonthOutput;
import com.ctoutweb.monsuivi.core.util.NumberUtil;

@CoreService
public class SoldAndBuyProductQuantityByMonthUseCase implements IUseCase<SoldAndBuyProductQuantityByMonthUseCase.Input, SoldAndBuyProductQuantityByMonthUseCase.Output> {
  private final ISoldAndBuyProductQuantityByMonthGateway<ISoldAndBuyProductQuantityByMonth> gateway;
  private final CoreFactory coreFactory = InstanceLoader.getCoreFactory();
  public SoldAndBuyProductQuantityByMonthUseCase(ISoldAndBuyProductQuantityByMonthGateway<ISoldAndBuyProductQuantityByMonth> baseGateway) {
    this.gateway = baseGateway;
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

    var output = coreFactory.getSoldAndBuyProductQuantityByMonthOutputImpl(
            datas,
            NumberUtil.getMonthFromNumber(month),
            year
    );

    return new Output(output);
  }

  public static class Input extends InputBase<ISoldAndBuyProductQuantityByMonthInput> implements IUseCase.Input {
    public Input(ISoldAndBuyProductQuantityByMonthInput inputPort) {
      super(inputPort);
    }
  }

  public static class Output extends OutputBase<ISoldAndBuyProductQuantityByMonthOutput> implements IUseCase.Output {

    public Output(ISoldAndBuyProductQuantityByMonthOutput outputBoundary) {
      super(outputBoundary);
    }
  }
}
