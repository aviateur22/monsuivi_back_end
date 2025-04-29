package com.ctoutweb.monsuivi.core.usecase;

import com.ctoutweb.monsuivi.core.annotation.CoreService;
import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.model.usecase.IUseCase;
import com.ctoutweb.monsuivi.core.model.usecase.InputBase;
import com.ctoutweb.monsuivi.core.model.usecase.OutputBase;
import com.ctoutweb.monsuivi.core.port.getProductDetail.IGetProductDetailGateway;
import com.ctoutweb.monsuivi.core.port.getProductDetail.IGetProductDetailInput;
import com.ctoutweb.monsuivi.core.port.getProductDetail.IGetProductDetailOutput;

@CoreService
public class GetProductDetailUseCase implements IUseCase<GetProductDetailUseCase.Input, GetProductDetailUseCase.Output> {
  private final IGetProductDetailGateway productDetailGateway;
  private final CoreFactory coreFactory = new CoreFactory();

  public GetProductDetailUseCase(IGetProductDetailGateway productDetailGateway) {
    this.productDetailGateway = productDetailGateway;
  }

  @Override
  public Output execute(Input input) {
    long sellerId = input.getUsecaseInput().getSellerId();
    long productId = input.getUsecaseInput().getProductId();

    if(!productDetailGateway.canSellerGetProductDetail(productId, sellerId))
      throw new CoreException("Vous ne pouvez pas afficher les détails de ce produit");

    IProductDetail productDetail = productDetailGateway.getProductDetail();

    return new Output( coreFactory.getProductDetailOutputImpl(
            productDetail, sellerId, "Les détails de votre produit"
    ));
  }

  public static class Input extends InputBase<IGetProductDetailInput> implements IUseCase.Input {
    public Input(IGetProductDetailInput inputPort) {
      super(inputPort);
    }
  }
  public static class Output extends OutputBase<IGetProductDetailOutput> implements IUseCase.Output {

    public Output(IGetProductDetailOutput outputBoundary) {
      super(outputBoundary);
    }
  }
}
