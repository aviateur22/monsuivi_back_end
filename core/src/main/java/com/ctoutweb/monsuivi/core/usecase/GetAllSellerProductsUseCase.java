package com.ctoutweb.monsuivi.core.usecase;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.factory.InstanceLoader;
import com.ctoutweb.monsuivi.core.port.common.ISellerProductsManagerGateway;
import com.ctoutweb.monsuivi.core.rule.ISellerProductsManagerRules;
import com.ctoutweb.monsuivi.core.usecase.base.IUseCase;
import com.ctoutweb.monsuivi.core.usecase.base.InputBase;
import com.ctoutweb.monsuivi.core.usecase.base.OutputBase;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsInput;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsOutput;

@CoreService
public class GetAllSellerProductsUseCase implements IUseCase<GetAllSellerProductsUseCase.Input, GetAllSellerProductsUseCase.Output> {

  private final ISellerProductsManagerRules sellerProductsManagerRules;

    public GetAllSellerProductsUseCase(ISellerProductsManagerGateway sellerProductsManagerGateway) {
    sellerProductsManagerRules = InstanceLoader.getCoreFactory().getSellerProductsManagerRuleImpl(sellerProductsManagerGateway);

  }
  @Override
  public Output execute(Input input) {
    long sellerId = input.getUsecaseInput().getUserId();

      // Par default on ne recupère pas les produits vendu des clients
      boolean areSoldProductVisible = false;

      var sellerProducts = sellerProductsManagerRules
            .initialiseRule()
            .getSellerProducts(sellerId)
            .filterByAreSoldProductVisible(areSoldProductVisible)
            .getProducts();

    var productsResponse = InstanceLoader.getCoreFactory().getGetAllProductsOutputImpl(
            "La liste de vos produits",
            sellerProducts
    );

    return new Output(productsResponse);
  }
  public static class Input extends InputBase<IGetAllProductsInput> implements IUseCase.Input {
    public Input(IGetAllProductsInput productsInput) {
      super(productsInput);
    }
  }

  public static class Output extends OutputBase<IGetAllProductsOutput> implements IUseCase.Output {

    public Output(IGetAllProductsOutput productsOutput) {
      super(productsOutput);
    }
  }
}
