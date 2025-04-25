package com.ctoutweb.monsuivi.core.port.addProduct;

import com.ctoutweb.monsuivi.core.entity.product.IProductToAdd;

public interface IAddProductInput {
  Long getUserId();
  IProductToAdd getProductToSell();
}
