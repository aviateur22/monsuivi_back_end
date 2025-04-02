package com.ctoutweb.monsuivi.core.addproduct.port;

import com.ctoutweb.monsuivi.core.entity.product.IProduct;

public interface IAddProductInput {
  Long getUserId();
  IProduct getProductToSell();
}
