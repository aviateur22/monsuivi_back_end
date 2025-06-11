package com.ctoutweb.monsuivi.core.usecase;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.usecase.base.IUseCase;
import com.ctoutweb.monsuivi.core.usecase.base.InputBase;
import com.ctoutweb.monsuivi.core.usecase.base.OutputBase;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsGateway;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsInput;
import com.ctoutweb.monsuivi.core.port.getAllSellerProducts.IGetAllProductsOutput;

@CoreService
public class GetAllSellerProductsUseCase implements IUseCase<GetAllSellerProductsUseCase.Input, GetAllSellerProductsUseCase.Output> {
  private final IGetAllProductsGateway getAllProductsGateway;

  public GetAllSellerProductsUseCase(IGetAllProductsGateway getAllProductsGateway) {
    this.getAllProductsGateway = getAllProductsGateway;
  }
  @Override
  public Output execute(Input input) {
    IGetAllProductsInput getAllProductsInput = input.getUsecaseInput();

    if(this.getAllProductsGateway.isSellerFind(getAllProductsInput.getUserId()) != true)
      throw new CoreException("Utilisateur inconnu");

    var getAllProducts = getAllProductsGateway.getAllProducts(
            getAllProductsInput.getUserId(),
            "La liste de vos produits");

    return new Output(getAllProducts);
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
