package com.ctoutweb.monsuivi.core.entity.addProduct;

import com.ctoutweb.monsuivi.core.addproduct.port.IAddProductInput;
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
