package com.ctoutweb.monsuivi.core.port.addProduct.impl;

import com.ctoutweb.monsuivi.core.port.addProduct.IAddProductInput;
import com.ctoutweb.monsuivi.core.entity.product.IProductToAdd;

public record AddProductImpl() implements IAddProductInput {
  @Override
  public Long getUserId() {
    return null;
  }

  @Override
  public IProductToAdd getProductToSell() {
    return null;
  }
}
