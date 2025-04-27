package com.ctoutweb.monsuivi.core.port.desactivateProduct;

import com.ctoutweb.monsuivi.core.entity.product.IProductDesactivate;

public interface IDesactivateProductGateway {
  boolean canSellerDesactivateProduct(long productId, long sellerId);

  IProductDesactivate desactivateProduct(long productId);

}
