package com.ctoutweb.monsuivi.core.port.addProduct;

import com.ctoutweb.monsuivi.core.entity.product.IProduct;

public interface IAddProductInput {
  Long getUserId();
  IProduct getProductToSell();
}
