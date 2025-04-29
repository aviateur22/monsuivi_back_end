package com.ctoutweb.monsuivi.core.port.productUpdate.impl;

import com.ctoutweb.monsuivi.core.port.productUpdate.IProductUpdateOuput;

public record ProductUpdateOutputImpl(
        long productId,
        String responseMessage
) implements IProductUpdateOuput {
  @Override
  public long getProductId() {
    return productId;
  }

  @Override
  public String getResponseMessage() {
    return responseMessage;
  }
}
