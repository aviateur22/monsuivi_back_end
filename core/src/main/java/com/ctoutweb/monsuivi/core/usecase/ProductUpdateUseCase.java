package com.ctoutweb.monsuivi.core.usecase;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.model.usecase.IUseCase;
import com.ctoutweb.monsuivi.core.model.usecase.InputBase;
import com.ctoutweb.monsuivi.core.model.usecase.OutputBase;
import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateGateway;
import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateInput;
import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateOuput;
import com.ctoutweb.monsuivi.core.port.productUpdate.impl.ProductUpdateOutputImpl;

@CoreService
public class ProductUpdateUseCase implements IUseCase<ProductUpdateUseCase.Input, ProductUpdateUseCase.Output> {
  private final IProductUpdateGateway productUpdateGateway;

  public ProductUpdateUseCase(IProductUpdateGateway productUpdateGateway) {
    this.productUpdateGateway = productUpdateGateway;
  }

  @Override
  public Output execute(Input input) {
    long sellerId = input.getUsecaseInput().getSellerId();
    long productId = input.getUsecaseInput().getProductId();
    IProductUpdateInput productUpdateInput = input.getUsecaseInput();

    if(!productUpdateGateway.canSellerUpdateProduct(productId, sellerId))
      throw new CoreException("Vous ne pouvez pas modifier les détails de ce produit");

    IProductDetail productDetailUpdated = productUpdateGateway.updateProduct(productUpdateInput);

    return new Output(
            new ProductUpdateOutputImpl(productDetailUpdated, "Votre produit a été mis a jour")
    );
  }

  public static class Input extends InputBase<IProductUpdateInput> implements IUseCase.Input {

    public Input(IProductUpdateInput inputPort) {
      super(inputPort);
    }
  }
  public static class Output extends OutputBase<IProductUpdateOuput> implements IUseCase.Output {

    public Output(IProductUpdateOuput outputBoundary) {
      super(outputBoundary);
    }
  }
}
