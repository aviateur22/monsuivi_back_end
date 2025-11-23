package com.ctoutweb.monsuivi.core.port.desactivateProduct.impl;

import com.ctoutweb.monsuivi.core.port.desactivateProduct.IUpdateProductActivationInput;

public record UpdateProductActivationInputImpl(long productId, long sellerId, boolean isProductActif) implements IUpdateProductActivationInput {
  @Override
  public long getProductId() {
    return productId;
  }

  @Override
  public long getSellerId() {
    return sellerId;
  }

  @Override
  public boolean getIsProductActivate() {
    return isProductActif;
  }
}
