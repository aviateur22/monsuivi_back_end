package com.ctoutweb.monsuivi.infra.adapter.updateProduct;

import com.ctoutweb.monsuivi.core.factory.CoreFactory;
import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateInput;
import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateOuput;
import com.ctoutweb.monsuivi.infra.dto.response.ProductUpdateResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductUpdateMapper {
  private final CoreFactory coreFactory;

  public ProductUpdateMapper(CoreFactory coreFactory) {
    this.coreFactory = coreFactory;
  }
  public IProductUpdateInput mapToInput(
          Long productId,
          Long sellerId,
          double productPurchasePrice,
          double productSoldPrice,
          LocalDate productSoldDate
  ) {
   return coreFactory.getProductUpdateInput(productId, sellerId, productPurchasePrice, productSoldPrice, productSoldDate);
  }

  public ProductUpdateResponseDto mapToResponseDto(IProductUpdateOuput output) {
    return new ProductUpdateResponseDto(
            output.getProductId(),
            output.getResponseMessage()
    );
  }
}
