package com.ctoutweb.monsuivi.core.usecase;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.entity.product.IFilterSellerProductsRule;
import com.ctoutweb.monsuivi.core.port.filterSellerProducts.IFilterSellerProductsGateway;
import com.ctoutweb.monsuivi.core.port.filterSellerProducts.IFilterSellerProductsInput;
import com.ctoutweb.monsuivi.core.port.filterSellerProducts.IFilterSellerProductsOutput;
import com.ctoutweb.monsuivi.core.usecase.base.IUseCase;
import com.ctoutweb.monsuivi.core.usecase.base.InputBase;
import com.ctoutweb.monsuivi.core.usecase.base.OutputBase;

@CoreService
public class FilterSellerProductsUseCase implements IUseCase<FilterSellerProductsUseCase.Input, FilterSellerProductsUseCase.Output> {
  private final IFilterSellerProductsRule filterSellerProductsRule;
  private final IFilterSellerProductsGateway filterSellerProductsGateway;

  public FilterSellerProductsUseCase(IFilterSellerProductsRule filterSellerProductsRule, IFilterSellerProductsGateway filterSellerProductsGateway) {
    this.filterSellerProductsRule = filterSellerProductsRule;
    this.filterSellerProductsGateway = filterSellerProductsGateway;
  }

  @Override
  public Output execute(Input input) {
    return null;
  }

  public static class Input extends InputBase<IFilterSellerProductsInput> implements IUseCase.Input {
    public Input(IFilterSellerProductsInput inputPort) {
      super(inputPort);
    }
  }

  public static class Output extends OutputBase<IFilterSellerProductsOutput> implements IUseCase.Output {
    public Output(IFilterSellerProductsOutput outputBoundary) {
      super(outputBoundary);
    }
  }
}
