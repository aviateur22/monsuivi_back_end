package com.ctoutweb.monsuivi.core.port.productUpdate.impl;

import com.ctoutweb.monsuivi.core.entity.product.IProductDetail;
import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateOuput;

public record ProductUpdateOutputImpl(
        IProductDetail productDetail,
        String responseMessage
) implements IProductUpdateOuput {
  @Override
  public IProductDetail getProductDetail() {
    return productDetail;
  }

  @Override
  public String getResponseMessage() {
    return responseMessage;
  }
}
