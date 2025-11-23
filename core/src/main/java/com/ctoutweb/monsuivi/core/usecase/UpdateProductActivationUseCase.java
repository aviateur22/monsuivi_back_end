package com.ctoutweb.monsuivi.core.usecase;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.entity.product.IUpdateProductActivation;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.usecase.base.IUseCase;
import com.ctoutweb.monsuivi.core.usecase.base.InputBase;
import com.ctoutweb.monsuivi.core.usecase.base.OutputBase;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IUpdateProductActivationGateway;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IUpdateProductActivationInput;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductOutput;

@CoreService
public class UpdateProductActivationUseCase implements IUseCase<UpdateProductActivationUseCase.Input, UpdateProductActivationUseCase.Output> {
  private final IUpdateProductActivationGateway updateProductActivationGateway;
  private final CoreFactory factory = new CoreFactory();

  public UpdateProductActivationUseCase(IUpdateProductActivationGateway updateProductActivationGateway) {
    this.updateProductActivationGateway = updateProductActivationGateway;
  }
  @Override
  public Output execute(Input input) {
    long productId = input.getUsecaseInput().getProductId();
    long sellerId = input.getUsecaseInput().getSellerId();
    boolean isProductActif = input.getUsecaseInput().getIsProductActivate();

    if(!updateProductActivationGateway.canSellerDesactivateProduct(productId, sellerId))
      throw new CoreException("Vous ne pouvez pas modifer ce produit");

    IUpdateProductActivation updateProduct = updateProductActivationGateway.updateProductActivation(productId, isProductActif);

    String updateResponseMessage = updateProduct.getIsProductActif() ? "Votre produit est activé" : "Votre produit est désactivé";

    return new Output(factory.getDesactivateProductOutputImpl(
            updateResponseMessage,
            updateProduct.getSellerId(),
            updateProduct.getProductId(),
            updateProduct.getIsProductActif())
    );
  }
  public static class Input extends InputBase<IUpdateProductActivationInput> implements IUseCase.Input{
    public Input(IUpdateProductActivationInput desactivateProductInput) {
      super(desactivateProductInput);
    }
  }

  public static class Output extends OutputBase<IDesactivateProductOutput> implements IUseCase.Output {
    public Output(IDesactivateProductOutput outputBoundary) {
      super(outputBoundary);
    }

  }
}
