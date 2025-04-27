package com.ctoutweb.monsuivi.core.usecase;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.entity.product.IProductDesactivate;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.model.usecase.IUseCase;
import com.ctoutweb.monsuivi.core.model.usecase.InputBase;
import com.ctoutweb.monsuivi.core.model.usecase.OutputBase;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductGateway;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductInput;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductOutput;

@CoreService
public class DesactivateProductUseCase implements IUseCase<DesactivateProductUseCase.Input, DesactivateProductUseCase.Output> {
  private final IDesactivateProductGateway desactivateProductGateway;
  private final CoreFactory factory = new CoreFactory();

  public DesactivateProductUseCase(IDesactivateProductGateway desactivateProductGateway) {
    this.desactivateProductGateway = desactivateProductGateway;
  }
  @Override
  public Output execute(Input input) {
    long productId = input.getUsecaseInput().getProductId();
    long sellerId = input.getUsecaseInput().getSellerId();

    if(!desactivateProductGateway.canSellerDesactivateProduct(productId, sellerId))
      throw new CoreException("Vous ne pouvez pas modifer ce produit");

    IProductDesactivate productDesactivate = desactivateProductGateway.desactivateProduct(productId);

    return new Output(factory.getDesactivateProductOutputImpl(
            "Votre produit est désactivé.",
            productDesactivate.getSellerId(),
            productDesactivate.getProductId(),
            productDesactivate.getIsProductActif())
    );
  }
  public static class Input extends InputBase<IDesactivateProductInput> implements IUseCase.Input{
    public Input(IDesactivateProductInput desactivateProductInput) {
      super(desactivateProductInput);
    }
  }

  public static class Output extends OutputBase<IDesactivateProductOutput> implements IUseCase.Output {
    public Output(IDesactivateProductOutput outputBoundary) {
      super(outputBoundary);
    }

  }
}
