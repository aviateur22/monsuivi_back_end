package com.ctoutweb.monsuivi.infra.adapter.desactivateProduct.mapper;

import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IUpdateProductActivationInput;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductOutput;
import com.ctoutweb.monsuivi.infra.dto.response.UpdateProductActivationDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class DesactivateProductMapper {
  private final CoreFactory coreFactory;

  public DesactivateProductMapper(CoreFactory coreFactory) {
    this.coreFactory = coreFactory;
  }

  /**
   * map vers IDesactivateProductInput
   * @param productId long
   * @param sellerId long
   * @return IDesactivateProductInput
   */
  public IUpdateProductActivationInput mapToUseCaseInput(long productId, long sellerId, boolean isProductActivate) {
    return coreFactory.getUpdateProductActivationInputImpl(productId, sellerId, isProductActivate);
  }

  /**
   * Map vers dto
   * @param desactivateProductOutput
   * @return
   */
  public UpdateProductActivationDtoResponse mapToDtoResponse(IDesactivateProductOutput desactivateProductOutput) {
    return new UpdateProductActivationDtoResponse(
            desactivateProductOutput.getProductId(),
            desactivateProductOutput.getSellerId(),
            desactivateProductOutput.getProductIsActif(),
            desactivateProductOutput.getResponseMessage());
  }
}
