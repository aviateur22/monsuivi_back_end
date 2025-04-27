package com.ctoutweb.monsuivi.core.port.desactivateProduct.impl;

import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductInput;

public record DesactivateProductInputImpl(long productId, long sellerId) implements IDesactivateProductInput {
  @Override
  public long getProductId() {
    return productId;
  }

  @Override
  public long getSellerId() {
    return sellerId;
  }
}
