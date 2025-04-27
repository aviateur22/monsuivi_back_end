package com.ctoutweb.monsuivi.infra.adapter.desactivateProduct.mapper;

import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductInput;
import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductOutput;
import com.ctoutweb.monsuivi.infra.dto.response.DesactivateProductDtoResponse;
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
  public IDesactivateProductInput mapToUseCaseInput(long productId, long sellerId) {
    return coreFactory.getDesactivateProductInputImpl(productId, sellerId);
  }

  /**
   * Map vers dto
   * @param desactivateProductOutput
   * @return
   */
  public DesactivateProductDtoResponse mapToDtoResponse(IDesactivateProductOutput desactivateProductOutput) {
    return new DesactivateProductDtoResponse(
            desactivateProductOutput.getProductId(),
            desactivateProductOutput.getSellerId(),
            desactivateProductOutput.getProductIsActif(),
            desactivateProductOutput.getResponseMessage());
  }
}
