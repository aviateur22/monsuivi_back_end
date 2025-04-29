package com.ctoutweb.monsuivi.core.port.getProductDetail.impl;

import com.ctoutweb.monsuivi.core.port.getProductDetail.IGetProductDetailInput;

public record GetProductDetailInputImpl(long productId, long sellerId) implements IGetProductDetailInput {
  @Override
  public long getProductId() {
    return productId;
  }

  @Override
  public long getSellerId() {
    return sellerId;
  }
}
