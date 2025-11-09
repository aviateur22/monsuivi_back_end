package com.ctoutweb.monsuivi.core.usecase;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;
import com.ctoutweb.monsuivi.core.rule.ISellerProductsManagerRules;
import com.ctoutweb.monsuivi.core.port.common.ISellerProductsManagerGateway;
import com.ctoutweb.monsuivi.core.port.filterSellerProducts.IFilterSellerProductsInput;
import com.ctoutweb.monsuivi.core.port.filterSellerProducts.IFilterSellerProductsOutput;
import com.ctoutweb.monsuivi.core.usecase.base.IUseCase;
import com.ctoutweb.monsuivi.core.usecase.base.InputBase;
import com.ctoutweb.monsuivi.core.usecase.base.OutputBase;

@CoreService
public class FilterSellerProductsUseCase implements IUseCase<FilterSellerProductsUseCase.Input, FilterSellerProductsUseCase.Output> {
  private final ISellerProductsManagerGateway filterSellerProductsGateway;
  private final CoreFactory coreFactory = InstanceLoader.getCoreFactory();
  private ISellerProductsManagerRules filterSellerProductsRule;

  public FilterSellerProductsUseCase(ISellerProductsManagerGateway filterSellerProductsGateway) {
    this.filterSellerProductsGateway = filterSellerProductsGateway;
    filterSellerProductsRule = coreFactory.getSellerProductsManagerRuleImpl(filterSellerProductsGateway);
  }

  @Override
  public Output execute(Input input) {
    long sellerId = input.getUsecaseInput().getSellerId();
    String filterByProductName= input.getUsecaseInput().getFilterProductByNameInput();
    String filterByProductCategoryInput = input.getUsecaseInput().getFilterProductByCategoryInput();
    Short filterByPeriodInDayInput = input.getUsecaseInput().getFilterPeriodInDayInput();
    boolean areProductSoldVisible = input.getUsecaseInput().getAreSoldProductVisible();

    var productFilteredList = filterSellerProductsRule
            .initialiseRule()
            .getSellerProducts(sellerId)
            .filterByProductName(filterByProductName)
            .filterByProductCategory(filterByProductCategoryInput)
            .filterByRegisterPeriod(filterByPeriodInDayInput)
            .filterByAreSoldProductVisible(areProductSoldVisible)
            .getProducts();

    return new Output(coreFactory.getFilterSellerProductsOutputImpl("La liste est filtrée", productFilteredList));
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
