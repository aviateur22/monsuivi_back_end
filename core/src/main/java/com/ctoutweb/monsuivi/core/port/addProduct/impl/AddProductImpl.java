package com.ctoutweb.monsuivi.core.port.addProduct.impl;

import com.ctoutweb.monsuivi.core.port.addProduct.IAddProductInput;
import com.ctoutweb.monsuivi.core.entity.product.IProduct;

public record AddProductImpl() implements IAddProductInput {
  @Override
  public Long getUserId() {
    return null;
  }

  @Override
  public IProduct getProductToSell() {
    return null;
  }
}
