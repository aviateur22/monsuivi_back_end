package com.ctoutweb.monsuivi.core.entity.product.impl;

import com.ctoutweb.monsuivi.core.entity.product.IUpdateProductActivation;

public record ProductDesacativateImpl(
        long sellerId,
        long productId,
        boolean isActif) implements IUpdateProductActivation {
  @Override
  public long getSellerId() {
    return sellerId;
  }

  @Override
  public long getProductId() {
    return productId;
  }

  @Override
  public boolean getIsProductActif() {
    return isActif;
  }
}
