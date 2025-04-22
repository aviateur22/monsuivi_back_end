package com.ctoutweb.monsuivi.core.usecase;

import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.model.usecase.IUseCase;
import com.ctoutweb.monsuivi.core.model.usecase.InputBase;
import com.ctoutweb.monsuivi.core.model.usecase.OutputBase;
import com.ctoutweb.monsuivi.core.port.displayAllProducts.IGetAllProductsGateway;
import com.ctoutweb.monsuivi.core.port.displayAllProducts.IGetAllProductsInput;
import com.ctoutweb.monsuivi.core.port.displayAllProducts.IGetAllProductsOutput;

public class DisplayAllProductsUseCase implements IUseCase<DisplayAllProductsUseCase.Input, DisplayAllProductsUseCase.Output> {
  private final IGetAllProductsGateway getAllProductsGateway;

  public DisplayAllProductsUseCase(IGetAllProductsGateway getAllProductsGateway) {
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
