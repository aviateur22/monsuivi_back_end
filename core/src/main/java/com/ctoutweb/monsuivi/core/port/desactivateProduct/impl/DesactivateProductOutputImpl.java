package com.ctoutweb.monsuivi.core.port.desactivateProduct.impl;

import com.ctoutweb.monsuivi.core.port.desactivateProduct.IDesactivateProductOutput;

public record DesactivateProductOutputImpl(
        String responseMessage,
        long sellerId,
        long productId,
        boolean productIsActif)
        implements IDesactivateProductOutput {
  @Override
  public String getResponseMessage() {
    return responseMessage;
  }

  @Override
  public long getSellerId() {
    return sellerId;
  }

  @Override
  public long getProductId() {
    return productId;
  }

  @Override
  public boolean getProductIsActif() {
    return productIsActif;
  }
}
