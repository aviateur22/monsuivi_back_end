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
          LocalDate productBuyDay,
          LocalDate productSoldDay,
          String productStatus
  ) {
   return coreFactory.getProductUpdateInput(productId, sellerId, productPurchasePrice, productSoldPrice, productBuyDay, productSoldDay, productStatus);
  }

  public ProductUpdateResponseDto mapToResponseDto(IProductUpdateOuput output) {
    return new ProductUpdateResponseDto(
            output.getProductDetail().getProductId(),
            output.getProductDetail().getProductPurchasePrice(),
            output.getProductDetail().getProductSoldPrice(),
            output.getProductDetail().getProductBuyAt(),
            output.getProductDetail().getProductSoldAt(),
            output.getProductDetail().getProductStatus(),
            output.getResponseMessage()
    );
  }
}
