package com.ctoutweb.monsuivi.core.usecase;

import com.ctoutweb.monsuivi.core.addproduct.port.IAddProductRepositoryGateway;
import com.ctoutweb.monsuivi.core.addproduct.port.IAddProductInput;
import com.ctoutweb.monsuivi.core.addproduct.port.IAddProductOutput;
import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.model.usecase.IUseCase;
import com.ctoutweb.monsuivi.core.model.usecase.InputBase;
import com.ctoutweb.monsuivi.core.model.usecase.OutputBase;

@CoreService
public class AddProductUseCase implements IUseCase<AddProductUseCase.Input, AddProductUseCase.Output>  {
  private final IAddProductRepositoryGateway addProductRepository;

  public AddProductUseCase(IAddProductRepositoryGateway addProductRepository) {
    this.addProductRepository = addProductRepository;
  }

  @Override
  public Output execute(Input input) {
    IAddProductInput productToAdd = input.getUsecaseInput();

    if(addProductRepository.isSellerFind(productToAdd.getUserId()) != true)
      throw new CoreException("Utilisateur inconnu");

    IAddProductOutput productOutput = addProductRepository.saveProductInformations(
            productToAdd.getProductToSell(),
            productToAdd.getUserId(),
            "Félicitation votre produit est ajouté");

    return new Output(productOutput);
  }

  public static class Input extends InputBase<IAddProductInput> implements IUseCase.Input {
    public Input(IAddProductInput productToAdd) {
      super(productToAdd);
    }
  }

  public static class Output extends OutputBase<IAddProductOutput> implements IUseCase.Output {

    public Output(IAddProductOutput productOutput) {
      super(productOutput);
    }
  }
}
